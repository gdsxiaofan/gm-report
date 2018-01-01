/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
import {fetch,qs} from '../lib/utils/fetch'

let base = '';

//修改密码
export function updatePwd(oldPassword, newPassword) {
  return fetch({
    url: `/v1/user/update/password`,
    method: 'put',
    data: {oldPassword, newPassword},
    // headers: {'Content-Type': 'application/x-www-form-urlencoded'}
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
export function getUserInfo() {
  return fetch({
    url: `v1/user/query`,
    method: 'get'
  })
}
