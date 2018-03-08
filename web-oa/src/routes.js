/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
import VueRouter from 'vue-router'
import iview from 'iview'
import {
  getCookie,
  delCookie
} from '../src/lib/utils/common'
//登录页
const Login = resolve => require(['./pages/Login.vue'], resolve)
//404
const NotFound = resolve => require(['./pages/404.vue'], resolve)
//页面主体
const Home = resolve => require(['./pages/Home.vue'], resolve)
//权限管理            >>>>>>
/*角色管理*/
const Role = resolve => require(['./pages/role/Role.vue'], resolve)
/*班组管理*/
const Group = resolve => require(['./pages/role/Group.vue'], resolve)
/*考勤管理*/
const attendance = resolve => require(['./pages/role/attendance.vue'], resolve)
/*设备管理*/
const device = resolve => require(['./pages/role/device.vue'], resolve)
/*用户角色管理*/
const ShowUser = resolve => require(['./pages/role/ShowUser.vue'], resolve)
/*文件下载*/
const fileLoad = resolve => require(['./pages/role/fileLoad.vue'], resolve)

let routes = [
  {
    path: '/',
    component: Home,
    meta: {
      title: '首页'
    },
    iconCls: 'ios-home', //图标样式class
    children: [
      // {path: 'role', meta: {title: '角色管理'}, icon: 'ios-home', component: Role},
      {path: 'group', meta: {title: '班组管理'}, icon: 'ios-home', component: Group},
      {path: 'device', meta: {title: '设备管理'}, icon: 'ios-home', component: device},
      {path: 'attendance', meta: {title: '考勤管理'}, icon: 'ios-home', component: attendance},
      {path: 'userRole', meta: {title: '用户管理'}, icon: 'ios-home', component: ShowUser},
      {path: 'fileLoad', meta: {title: '文件下载'}, icon: 'ios-home', component: fileLoad},


    ]
  },
  {
    path: '/login',
    component: Login,
    name: '',
    hidden: true
  },
  {
    path: '/404',
    component: NotFound,
    name: '',
    hidden: true
  },
  {
    path: '*',
    hidden: true,
    redirect: {path: '/print'}
  }
];

const router = new VueRouter({
  routes
})
router.beforeEach((to, from, next) => {
  iview.LoadingBar.start();
  if (to.path === '/login') {
    delCookie('Authorization');
  }
  let jwt = getCookie("Authorization");
  if (!jwt && to.path !== '/login') {
    next({path: '/login', query: {redirect: to.fullPath}})
    iview.LoadingBar.finish();
    window.scrollTo(0, 0);
  } else {
    next()
  }
})

router.afterEach(() => {
  iview.LoadingBar.finish();
  window.scrollTo(0, 0);
});

export default router;
