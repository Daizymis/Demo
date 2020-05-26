import Vue from 'vue'
import Router from 'vue-router'
import init from './components/initialize'
import idle from './components/idle'
import roommap from './components/roommap/roommap'
import getCoordinate from './components/roommap/getCoordinate'
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
    {
      path: '/room-map',
      name: 'roommap',
      component: roommap
    },
    {
      path: '/get-coordinate',
      name: 'getCoordinate',
      component: getCoordinate
    },
  ]
})
