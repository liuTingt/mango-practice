/**
 * 封装mock模块，方便地定制模拟接口的统一开关和个体开关
 */


 // index.js是聚合模块，统一导入所有子模块并通过调用mock进行数据模拟
import Mock from 'mockjs'
import { baseMockUrl } from '@/utils/global'
import * as login from './modules/login'
import * as user from './modules/user'
import * as role from './modules/role'
import * as dept from './modules/dept'
import * as menu from './modules/menu'
import * as dict from './modules/dict'
import * as config from './modules/config'
import * as log from './modules/log'
import * as loginlog from './modules/loginlog'

/**
 * 1、开启/关闭【所有模块】拦截，通过调用【openMock参数】设置
 * 2、开启/关闭【业务模块】拦截，通过调用fnCreate方法【isOpen参数】设置
 * 3、开启/关闭【业务模块中某个请求】拦截，通过函数返回对象中的【isOpen属性】设置
 */

 let openMock = true;
 fnCreate(login, openMock)
 fnCreate(user, openMock)
 fnCreate(role, openMock)
 fnCreate(dept, openMock)
 fnCreate(menu, openMock)
 fnCreate(dict, openMock)
 fnCreate(config, openMock)
 fnCreate(log, openMock)
 fnCreate(loginlog, openMock)

/**
 * 创建mock模拟数据
 * @param {*} mod 模块
 * @param {*} isOpen 是否开启
 */
 function fnCreate(mod, isOpen = true){
    if(isOpen) {
        for(var key in mod) {
            ((res) => {                
                if(res.isOpen !== false) {
                    let url = baseMockUrl;
                    if(!url.endsWith("/")) {
                        url = url + "/";
                    }
                    url = url + res.url;
                    console.log(new RegExp(url))
                    Mock.mock(new RegExp(url), res.type, (opts) => {
                        opts['data'] = opts.body ? JSON.parse(opts.body) : null;
                        delete opts.body;
                        console.log('\n');
                        console.log('%cmock拦截，请求：', 'color:blue', opts);
                        console.log('%cmock拦截，响应：', 'color:blue', res.data);
                        return res.data;
                    })
                }
            })(mod[key]() || {})
        }
    }
 }