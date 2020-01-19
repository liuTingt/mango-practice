import axios from '../axios'

/* 
 * 系统配置模块
 */

// 保存
export const save = (data) => {
    return axios({
        url: '/sys-config/save',
        method: 'post',
        data
    })
}
// 删除
export const batchDelete = (data) => {
    return axios({
        url: '/sys-config/delete',
        method: 'post',
        data
    })
}
// 分页查询
export const findPage = (data) => {
    return axios({
        url: '/sys-config/findPage',
        method: 'post',
        data
    })
}