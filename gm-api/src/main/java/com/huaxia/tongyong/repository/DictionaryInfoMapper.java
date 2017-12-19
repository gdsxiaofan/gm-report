package com.huaxia.tongyong.repository;

import com.huaxia.tongyong.model.DictionaryInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 
 * DictionaryInfoMapper数据库操作接口类
 * 
 **/

public interface DictionaryInfoMapper{

    /**
     * 根据字典类型查询字典列表数据
     * @param dictionaryType
     * @return
     */
    public List<DictionaryInfo> selectDictionaryInfoByType(
            @Param("dictionaryType")Integer dictionaryType
    );
}