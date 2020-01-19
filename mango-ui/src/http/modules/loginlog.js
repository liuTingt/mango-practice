import axios from '../axios'

// 删除
export const batchDelete = (data) => {
    return axios({
        url: '/sys-loginlog/delete',
        method: 'post',
        data
    })
}
// 分页查询
export const findPage = (data) => {
    return axios({
        url: '/sys-loginlog/findPage',
        method: 'post',
        data
    })
}