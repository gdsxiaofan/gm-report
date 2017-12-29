/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)
// 功能列
const Home = resolve => require(['../view/Home'], resolve)
// 功能列
const Main = resolve => require(['../view/report/main'], resolve)
// 日报list信息
const reportList = resolve => require(['../view/report/reportList'], resolve)
// 日报info
const reportInfo = resolve => require(['../view/report/reportInfo'], resolve)
// 日报交接
const reportTransfer = resolve => require(['../view/report/reportTransfer'], resolve)
// 用户登录
const Login = resolve => require(['../view/report/login'], resolve)

// 个人中心
const Personal = resolve => require(['../view/report/personal'], resolve)

const router = new Router({

  routes: [
    {path: '/login', component: Login},
    {
      path: '/',
      component: Home,
      redirect: '/main',
      children: [
        {path: '/main', component: Main},
        {path: '/reportList', component: reportList},
        {path: '/reportInfo', component: reportInfo},
        {path: '/personal', component: Personal},
        {path: '/reportTransfer', component: reportTransfer},
        ]
    }
  ]
})

router.beforeEach((to, from, next) => {
  if (to.path === '/login') {
    localStorage.removeItem('Authorization')
  }
  let jwt = localStorage.getItem('Authorization')
  if (!jwt && to.path !== '/login') {
    next({path: '/login', query: {redirect: to.fullPath}})
    window.scrollTo(0, 0)
  } else {
    next()
  }
})
router.afterEach(() => {
  window.scrollTo(0, 0)
})
export default router
