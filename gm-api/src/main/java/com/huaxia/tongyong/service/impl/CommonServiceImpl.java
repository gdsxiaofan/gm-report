package com.huaxia.tongyong.service.impl;

import com.huaxia.tongyong.enums.LevelEnum;
import com.huaxia.tongyong.param.SelectOption;
import com.huaxia.tongyong.service.CommonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2018/1/22
 * @since 1.0.0
 */
@Service
public class CommonServiceImpl implements CommonService{
    @Override
    public List<SelectOption> getSelectOptionByType(int optionType) {
        List<SelectOption> selectOptions = new ArrayList<>();
        for(LevelEnum levelEnum:LevelEnum.values()){
            SelectOption selectOption = new SelectOption();
            selectOption.setOptionCode(levelEnum.getCode());
            selectOption.setOpitonName(levelEnum.getName());
            selectOptions.add(selectOption);
        }

        return selectOptions;
    }
}
