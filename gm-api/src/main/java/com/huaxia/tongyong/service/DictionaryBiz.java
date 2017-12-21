/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.huaxia.tongyong.service;

import com.huaxia.tongyong.model.DictionaryInfo;

import java.util.List;

/**
 * 字典管理类
 *
 * @author gongdaoshun
 * @date 2017/12/21 0021
 * @since 1.0.0
 */
public interface DictionaryBiz {

    /**
     * 获取字典数据
     * @param type
     * @return
     */
    public List<DictionaryInfo> getDictionaryInfoList(Integer type);
}
