// 存储IFrame状态
export default {
    state: {
        iframeUrl: [], // 当前嵌套页面路由路径
        iframeUrls: [] // 所有嵌套页面路由路径访问URL
    },
    getters: {

    },
    mutations: {
        setIframeUrl(state, iframeUrl) { // 设置iframeUrl
            state.iframeUrl = iframeUrl
        },
        addIframeUrl(state, iframeUrl) {
            state.iframeUrls.push(iframeUrl)
        }
    },
    actions: {
        
    }
}