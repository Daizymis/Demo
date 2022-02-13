import Vue from 'vue'
import App from './App.vue'
import pcRouter from './router'
import mobileRouter from './route/mobile-route'
import store from './store'
import VueI18n from 'vue-i18n'
import axios from 'axios'
import './modules/vant'

Vue.use(VueI18n)
console.log(navigator.userAgent)
const router =navigator.userAgent.match(
  /(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i
) ? mobileRouter : pcRouter;

export const i18n = new VueI18n({
  locale: 'zh',  // 语言标识
  messages: {
    'zh': require('./common/messages_zh'),
    'en': require('./common/messages_en')
  }
})
Vue.config.productionTip = false

axios.defaults.headers.post["Content-Type"] = "application/json;charset=UTF-8";
axios.defaults.headers.put["Content-Type"] = "application/json;charset=UTF-8";
Vue.prototype.$axios = axios
new Vue({
  router,
  store,
  i18n,
  render: h => h(App)
}).$mount('#app')
