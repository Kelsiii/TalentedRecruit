import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/views/Login'
import SideMenu from '@/views/SideMenu'
import Setting from '@/views/info/Setting'
import Info from '@/views/info/Info'
import InfoEdit from '@/views/info/InfoEdit'
import PositionList from '@/views/position/PositionList'
import PositionOperate from '@/views/position/Operate'
import QuestionList from '@/views/question/QuestionList'
import QuestionOperate from '@/views/question/Operate'

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
      component: Info
    }, {
      path: '/info/edit',
      name: 'InfoEdit',
      component: InfoEdit
    }, {
      path: '/positionlist/:type/:status',
      name: 'PositionList',
      component: PositionList
    }, {
      path: '/position/:operation/:id',
      name: 'PositionOperate',
      component: PositionOperate
    }, {
      path: '/questionlist/:type',
      name: 'QuestionList',
      component: QuestionList
    }, {
      path: '/question/:operation/:id',
      name: 'QuestionOperate',
      component: QuestionOperate
    }

      // ...其他子路由
    ]
  }]
})
