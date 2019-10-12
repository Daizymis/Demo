import Vue from 'vue'
import Router from 'vue-router'
import init from './components/initialize'
import idle from './components/idle'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'init',
      component: init
    },
    {
      path: '/idle',
      name: 'idle',
      component: idle
    },
  ]
})
