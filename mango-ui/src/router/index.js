import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '@/store'
import api from '@/http/api'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import NotFound from '../views/404.vue'
import About from '../views/About.vue'
//import { Form } from 'element-ui'
import Intro from '../views/Intro/Intro'
import { getIFramePath, getIFrameUrl } from '@/utils/iframe'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: '首页',
    component: Home,
    children: [
      {
        path: '',
        name: '系统介绍',
        component: Intro,
        meta: {
          icon: 'fa fa-home fa-lg',
          index: 0
        }
      }
    ]
  },
  {
    path: '/about',
    name: 'about',
    component: About
  },
  {
    path: '/login',
    name: '登录',
    component: Login
  },
  {
    path: '/404',
    name: 'notFound',
    component: NotFound
  }
]

const router = new VueRouter({
  routes
})


// 路由对象router提供了beforeEach方法，可以在每次路由前进行一些相关处理，也叫导航守卫，这里我们通过导航守卫实现动态菜单的加载
router.beforeEach((to, from, next) => {
  // 登录界面登陆成功之后，会把用户信息保存在会话中，存在时间为会话生命周期，页面关闭即失效
  let userName = sessionStorage.getItem('user')
  if(to.path === '/login') {
    // 如果访问登录界面，且用户信息存在，则跳转到主页面
    if(userName) {
        next({ path: '/' })
    } else {
      next()
    }
  } else {
    if(!userName) {
      // 如果访问非登录界面，且用户信息不存在，代表未登录，则跳转到登录界面
      next({ path: '/login' })
    } else {
      // 加载动态菜单和路由
      addDynamicMenuAndRoutes(userName, to, from)
      next()
    }
  }
})


/**
 * 加载动态菜单和路由
 */
function addDynamicMenuAndRoutes(userName, to, from) {
  // 处理IFrame嵌套页面
  handleIFrameUrl(to.path)
  console.log("from URL: "+from)
  if(store.state.app.menuRouteLoaded) {
    console.log('动态菜单和路由已经存在')
    return
  } 
  api.menu.findNavTree({'userName': userName})
    .then(res => {
      // 添加动态路由
      let dynamicRoutes = addDynamicRoutes(res.data)
      router.options.routes[0].children = router.options.routes[0].children.concat(dynamicRoutes)
      router.addRoutes(router.options.routes)
      // 保存加载状态
      store.commit('menuRouteLoaded', true)
      // 保存菜单树
      store.commit('setNavTree', res.data)
    }).then(() => {
      api.user.findPermissions({'userName': userName}).then(res => {
        // 保存用户权限标识集合
        store.commit('setPerms', res.data)
      })
    }).catch(function(res) {
      console.log(res)
    })

}

/***
 * 处理IFrame嵌套页面
 */
function handleIFrameUrl(path) {
  // 嵌套页面，保存iframeURL到store，供IFrame组件读取展示
  let url = path
  let length = store.state.iframe.iframeUrls.length
  for(let i = 0; i < length; i++) {
    let iframe = store.state.iframe.iframeUrls[i]
    if(path != null && path.endsWith(iframe.path)) {
        url = iframe.url
        store.commit('setIframeUrl', url)
        break
    }
  }
}

/**
 * 添加动态（菜单）路由
 * @param {*} menuList 菜单列表
 * @param {*} routes 递归创建的动态（菜单）路由
 */
function addDynamicRoutes(menuList = [], routes = []) {
  var temp = []
  for(var i = 0; i < menuList.length; i++) {
    // 菜单为系统一级菜单（系统管理等等）
    if(menuList[i].children && menuList[i].children.length >= 1) {
      temp = temp.concat(menuList[i].children)
    }else if(menuList[i].url && /\S/.test(menuList[i].url)) { // 菜单为二级菜单时（用户/机构管理等）创建路由，url存在并且非空（/\S/）匹配非空白字符
      menuList[i].url = menuList[i].url.replace(/^\//, '') // 将开头的/替换为空
      // 创建路由
      var route = {
        path: menuList[i].url,
        component: null,
        name: menuList[i].name,
        meta: {
          icon: menuList[i].icon,
          index: menuList[i].id
        }
      }
      let path = getIFramePath(menuList[i].url)
      if(path) {
        // 如果是嵌套页面，通过iframe展示
        route['path'] = path
        route['component'] = resolve => require([`@/views/IFrame/IFrame`], resolve)
        // 存储嵌套页面路由路径和访问URL
        let url = getIFrameUrl(menuList[i].url)
        let iFrameUrl = {'path': path, 'url': url}
        store.commit('addIframeUrl', iFrameUrl)
      } else{
        try {
          // 根据菜单url动态加载vue组件，这里要求vue组件必须按照url路径存储，如url=‘sys/user’,则组件路径应是‘@/views/sys/user.vue’，否则组件加载不到
          let array = menuList[i].url.split('/')
          let url = ''
          for(let i = 0; i < array.length; i++) {
            url += array[i].substring(0,1).toUpperCase() + array[i].substring(1) + '/'
          }
          url = url.substring(0, url.length - 1)
          route['component'] = resolve => require([`@/views/${url}`], resolve)
        }catch(e) {
          console.log(e)
        }
      }
      routes.push(route)
    }
  }
  // 递归添加菜单路由
  if(temp.length >= 1) {
    addDynamicRoutes(temp, routes)
  } else {
    console.log('动态路由加载...')
    console.log(routes)
    console.log('动态路由加载完成.')
  }
  return routes
}




export default router
