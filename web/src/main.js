// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.

import router from './router'
import axios from 'axios'
import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import 'element-ui/lib/theme-chalk/base.css'
import CollapseTransition from 'element-ui/lib/transitions/collapse-transition'
import 'font-awesome/css/font-awesome.min.css'
import './assets/main.css'
import Cube from 'cube-ui'
import touch from 'vue-directive-touch';
//import md5 from 'js-md5';
//import Base64 from 'js-base64'

Vue.config.productionTip = false
Vue.prototype.$ajax = axios
global.router = router;

Vue.use(ElementUI);
Vue.use(Cube);
Vue.use(touch);
//Vue.use(md5);
//Vue.use(Base64);
Vue.component(CollapseTransition.name, CollapseTransition)

new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
  //render: h => h(App)
})

