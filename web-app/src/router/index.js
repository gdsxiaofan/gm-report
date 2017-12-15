/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)
// 功能列
const Main = resolve => require(['../view/report/main'], resolve)
// 日报信息
const reportInfo = resolve => require(['../view/report/reportInfo'], resolve)

// 用户登录
const Login = resolve => require(['../view/report/login'], resolve)

// 个人中心
const Personal = resolve => require(['../view/report/personal'], resolve)




const router=new Router({
  routes: [
    {path: '/main', component: Main},
    {path: '/reportInfo', component: reportInfo},
    {path: '/personal', component: Personal},
    {path: '/login', component: Login},
    {path: '/', redirect: 'main'}
  ]
})




router.beforeEach((to, from, next) => {
  if (to.path === '/login') {
    localStorage.removeItem('Authorization');
  }
  let jwt = localStorage.getItem("Authorization");
  if (!jwt && to.path !== '/login') {
    next({ path: '/login',query:{ redirect: to.fullPath} })
    window.scrollTo(0, 0);
  } else {
    next()
  }
})
router.afterEach(() => {
  window.scrollTo(0, 0);
});
export default  router
