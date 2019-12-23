import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import api from './http'
import global from '@/utils/global'
import Router from 'vue-router'
import './mock/index'
import 'font-awesome/css/font-awesome.min.css'
import i18n from './i18n'



/**
 * 第一次修改：导入API模块，并通过Vue.use(api)语句进行使用注册，这样可以通过“this.$api.子模块.方法”的方式来调用后台接口
 *            引入global模块，并通过Vue.prototype.global = global语句进行挂载，这样就可以通过“this.global.xx”来获取全局配置
 */
Vue.config.productionTip = false

Vue.use(ElementUI)
Vue.use(api)
Vue.use(i18n)


Vue.prototype.global = global // 挂载全局配置模块

new Vue({
  i18n, //把 i18n 挂载到 vue 根实例上
  router,
  store,
  render: h => h(App)
}).$mount('#app')


// 控制台报错：vue-router.esm.js?8c4f:2051 Uncaught (in promise) 解决方案
const originalPush = Router.prototype.push;
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err);
}