import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/views/Login'
import SideMenu from '@/views/SideMenu'
import Setting from '@/views/Setting'
import Info from '@/views/Info'
import InfoEdit from '@/views/InfoEdit'

Vue.use(Router)

export default new Router({
  routes: [{
    path: '/',
    name: 'Login',
    component: Login
  }, {
    path: '/dashboard',
    name: 'SideMenu',
    component: SideMenu,

    children: [{
      path: '/setting',
      name: 'Setting',
      component: Setting
    }, {
      path: '/info',
      name: 'Info',
      component: Info,
    }, {
      path: '/info/edit',
      name: 'InfoEdit',
      component: InfoEdit
    }

      // ...其他子路由
    ]
  }]
})
