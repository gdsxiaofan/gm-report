/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
import {fetch,qs} from '../utils/fetch'
import config from '../../config'
//日报列表接口
export function getReportList(reportQueryParams) {
  return fetch({
    url: `v1/report/list`,
    method: 'post',
    data:reportQueryParams
  })
}
//修复验证
export function addRepair(repairParam) {
  return fetch({
    url: `v1/repair/add`,
    method: 'post',
    data:repairParam
  })
}
export function getRepair(reportId) {
  return fetch({
    url: `v1/repair/get`,
    method: 'get',
    params:{reportId}
  })
}
export function updateRepair(repairParam) {
  return fetch({
    url: `v1/repair/update`,
    method: 'post',
    data:repairParam
  })
}
export function deleteRepair(reportId) {
  return fetch({
    url: `v1/repair/delete`,
    method: 'delete',
    params:{reportId}
  })
}
//故障日报
export function addFault(faultParam) {
  return fetch({
    url: `v1/fault/add`,
    method: 'post',
    data:faultParam
  })
}
export function getFault(reportId) {
  return fetch({
    url: `v1/fault/get`,
    method: 'get',
    params:{reportId}
  })
}
export function updateFault(faultParam) {
  return fetch({
    url: `v1/fault/update`,
    method: 'post',
    data:faultParam
  })
}
export function deleteFault(reportId) {
  return fetch({
    url: `v1/fault/delete`,
    method: 'delete',
    params:{reportId}
  })
}
//移交日报信息
export function transferReport(userId,reportIds) {
  return fetch({
    url: `v1/report/transfer`,
    method: 'post',
    params:{userId,reportIds}
  })
}
