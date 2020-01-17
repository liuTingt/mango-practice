/**
 * 全局常量、方法封装模块
 * 通过原型挂载到Vue属性
 * 通过this.global调用
 */

// 后台管理系统服务器地址
export const baseUrl = 'http://localhost:8001'
//export const baseUrl = 'http://localhost:8010/mongo-admin'
// 系统数据备份还原服务器地址
export const backupBaseUrl = 'http://localhost:8002'
// mock测试地址
export const baseMockUrl = 'http://localhost:8081'

export default {
    baseUrl,
    backupBaseUrl,
    baseMockUrl
}