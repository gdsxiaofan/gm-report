package com.huaxia.tongyong.service;

import com.huaxia.tongyong.param.SelectOption;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2018/1/22
 * @since 1.0.0
 */
public interface CommonService {
    /**
     * 根据类型获得下拉选框
     * @param optionType
     * @return
     */
    public List<SelectOption> getSelectOptionByType(int optionType);
}
