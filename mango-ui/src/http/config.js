/***
 * AXIOS相关配置，
 * 
 */
import { baseMockUrl } from '@/utils/global'

export default {
    method : 'get',
    // 基础url前缀
    baseUrl : baseMockUrl, 
    // 请求头信息
    headers : {
        'Content-Type' : 'application/json,charset=UTF-8'
    },
    // 参数
    data : {},
    // 设置超时时间
    timeout : 1000,
    // 携带凭证
    withCredentials: true,
    // 返回数据类型
    responseType: 'json'
}