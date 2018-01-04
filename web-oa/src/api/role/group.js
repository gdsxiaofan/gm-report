/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
import {fetch,qs} from '../../lib/utils/fetch'

export function getgroupList(queryCondition) {
  return fetch({
    url: 'v1/group/list',
    method: 'post',
    data: queryCondition
  });
}
export function addgroup(group) {
  return fetch({
    url: 'v1/group/add',
    method: 'post',
    data: group
  });
}
export function updategroup(group) {
  return fetch({
    url: 'v1/group/update',
    method: 'put',
    data: group
  });
}
export function delgroup(id) {
  return fetch({
    url: 'v1/group/del',
    method: 'delete',
    params: {id}
  });
}

