/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
import {fetch,qs} from '../../lib/utils/fetch'

export function getdeviceList(queryCondition) {
  return fetch({
    url: 'v1/device/list',
    method: 'post',
    data: queryCondition
  });
}
export function adddevice(device) {
  return fetch({
    url: 'v1/device/add',
    method: 'post',
    data: device
  });
}
export function updatedevice(device) {
  return fetch({
    url: 'v1/device/update',
    method: 'put',
    data: device
  });
}
export function deldevice(id) {
  return fetch({
    url: 'v1/device/del',
    method: 'delete',
    params: {id}
  });
}

