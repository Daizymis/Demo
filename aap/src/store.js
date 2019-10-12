import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex);

axios.defaults.headers.post["Content-Type"] = "application/json;charset=UTF-8";
axios.defaults.headers.put["Content-Type"] = "application/json;charset=UTF-8";
axios.defaults.baseURL = '/hm';

export default new Vuex.Store({
  modules: {
  },

  state: {
    terminal: {},
  },

  mutations: {
    setTerminal (state, terminal) {
      state.terminal = terminal;
    },
  },

  getters: {
    terminal: state => state.terminal,
  },

  actions: {
  }
})
