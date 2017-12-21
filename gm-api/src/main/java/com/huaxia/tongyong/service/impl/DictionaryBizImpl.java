/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.huaxia.tongyong.service.impl;

import com.huaxia.tongyong.model.DictionaryInfo;
import com.huaxia.tongyong.repository.DictionaryInfoMapper;
import com.huaxia.tongyong.service.DictionaryBiz;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典数据相关接口
 *
 * @author gongdaoshun
 * @date 2017/12/21 0021
 * @since 1.0.0
 */
@Service
public class DictionaryBizImpl implements DictionaryBiz{

    @Autowired
    private DictionaryInfoMapper dictionaryInfoMapper;

    /**
     * 根据字典类型获取字典数据
     * @param type
     * @return
     */
    @Override
    public List<DictionaryInfo> getDictionaryInfoList(Integer type) {
        Assert.notNull(type,"type字典类型不能为空");
        return dictionaryInfoMapper.selectDictionaryInfoByType(type);
    }
}
