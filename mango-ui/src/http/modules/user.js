/**
 * 用户管理模块
 */
import axios from '../axios'

export const save = (data) => {
    return axios({
        url: '/sys-user/save',
        method: 'post',
        data
    })
}

export const batchDelete = (data) => {
    return axios({
        url: '/sys-user/delete',
        method: 'post',
        data
    })
}

export const findPage = (data) => {
    return axios({
        url: '/sys-user/findPage',
        method: 'post',
        data
    })
}

export const getPage = (data) => {
    return axios({
        url: '/sys-user/getPage',
        method: 'post',
        data
    })
}

// 查找用户的菜单权限标识集合
export const findPermissions = (params) => {
    return axios({
        url: '/sys-user/findPermissions',
        method: 'post',
        params
    })
}


// 查找所有用户
export const findAll = (params) => {
    return axios({
        url: '/sys-user/findAll',
        method: 'get',
        params
    })
}