import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import app from './modules/app'

const store = new Vuex.Store({
  modules: {
    app: app
  }
})

export default store

// export default new Vuex.Store({
//   state: {
//   },
//   mutations: {
//   },
//   actions: {
//   },
//   modules: {
//   }
// })
