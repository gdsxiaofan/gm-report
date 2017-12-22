/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
import {fetch,qs} from '../utils/fetch'
import config from '../../config'
//日报列表接口
export function getReportList(reportQueryParams) {
  return fetch({
    url: `vi/report/list`,
    method: 'post',
    data:reportQueryParams
  })
}


