import axios from '../axios'

/* 
 * 角色管理模块
 */

// 保存
export const save = (data) => {
    return axios({
        url: '/sys-role/save',
        method: 'post',
        data
    })
}
// 删除
export const batchDelete = (data) => {
    return axios({
        url: '/sys-role/delete',
        method: 'post',
        data
    })
}
// 分页查询
export const findPage = (data) => {
    return axios({
        url: '/sys-role/findPage',
        method: 'post',
        data
    })
}
// 查询全部
export const findAll = () => {
    return axios({
        url: '/sys-role/findAll',
        method: 'post'
    })
}
// 查询角色菜单集合
export const findRoleMenus = (params) => {
    return axios({
        url: '/sys-role/findRoleMenus',
        method: 'post',
        params
    })
}
// 保存角色菜单集合
export const saveRoleMenus = (data) => {
    return axios({
        url: '/sys-role/saveRoleMenus',
        method: 'post',
        data
    })
}