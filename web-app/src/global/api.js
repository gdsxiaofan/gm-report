/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
import {fetch,qs} from '../utils/fetch'
import config from '../../config'
//修改密码
export function updatePwd(oldPwd, newPwd) {
  return fetch({
    url: `/v1/user/updatePwd`,
    method: 'put',
    data: qs.stringify({oldPwd, newPwd}),
    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
  })
}

//登录
export function login(username, password) {
  return fetch({
    url: `login`,
    method: 'post',
    data: qs.stringify({username, password}),
    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
  })
}
//获取用户相关信息（菜单，个人信息等）
export function getDictionaryInfoByType(type) {
  return fetch({
    url: `/v1/dictionary/get`,
    method: 'get',
    params:{type}
  })
}
//获取用户相关信息（菜单，个人信息等）
export function getUserInfo() {
  return fetch({
    url: `v1/user/query`,
    method: 'get'
  })
}




let url={
  apiUrl: "/ccp-web/api", //接口路径
  ctxPath: "/dist", //node服务dist目录
  //staticPath:config.dev.staticPath,       //开发环境静态资源路径
  staticPath: process.env.NODE_ENV !== 'development' ?  config.build.staticPath: config.dev.staticPath
}
export const api={
  'home':url.staticPath+'dataJson/home.json',
  'classify':url.staticPath+'dataJson/classify.json',
  'login':url.staticPath+'dataJson/login.json',
  'captchas':'/v1/captchas'
}
