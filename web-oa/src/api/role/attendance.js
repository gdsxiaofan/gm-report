/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
import {fetch,qs} from '../../lib/utils/fetch'

export function getattendanceList(queryCondition) {
  return fetch({
    url: 'v1/attendance/list',
    method: 'post',
    data: queryCondition
  });
}
export function addattendance(attendance) {
  return fetch({
    url: 'v1/attendance/add',
    method: 'post',
    data: attendance
  });
}
export function updateattendance(attendance) {
  return fetch({
    url: 'v1/attendance/update',
    method: 'put',
    data: attendance
  });
}
export function delattendance(id) {
  return fetch({
    url: 'v1/attendance/del',
    method: 'delete',
    params: {id}
  });
}

