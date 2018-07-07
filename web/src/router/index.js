import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/views/Login'
import SideMenu from '@/views/SideMenu'
import Setting from '@/views/info/Setting'
import Info from '@/views/info/Info'
import InfoEdit from '@/views/info/InfoEdit'
import PositionList from '@/views/position/PositionList'
import PositionOperate from '@/views/position/Operate'
import PostitionDetail from '@/views/position/Detail'
import QuestionList from '@/views/question/QuestionList'
import QuestionOperate from '@/views/question/Operate'
import CVDetail from '@/views/cv/Detail'
import Robot from '@/views/Robot'
import PositionDetailMobile from '@/views/position/DetailMobile'
import Examination from '@/views/question/ExamMobile'
import CVInput from '@/views/cv/InputMobile'



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
      path: '/position-detail/:id',
      name: 'PositionDetail',
      component: PostitionDetail
    }, {
      path: '/questionlist/:type',
      name: 'QuestionList',
      component: QuestionList
    }, {
      path: '/question/:operation/:id',
      name: 'QuestionOperate',
      component: QuestionOperate
    }, {
      path: '/cv/:position_id/:cv_id',
      name: 'CVDetail',
      component: CVDetail
    }

      // ...其他子路由
    ]
  }, {
    path: '/:company_id/mobile/chat',
    name: 'Robot',
    component: Robot
  }, {
    path: '/:company_id/mobile/position/:position_id',
    name: 'Position',
    component: PositionDetailMobile
  }, {
    path: '/:company_id/mobile/examination/:position_id',
    name: 'Examination',
    component: Examination
  }, {
    path: '/:company_id/mobile/cvinput/:position_id',
    name: 'CVInput',
    component: CVInput
  }]
})
