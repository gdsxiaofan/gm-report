package com.huaxia.tongyong.enums;

import lombok.Getter;

/**
 * @Description:
 * @author: gongdaoshun
 * @Date: 2017/12/28 20:48
 * @className: LevelEnum
 */
public enum LevelEnum{
    ONE(1,"一级技师"),
    TWO(2,"二级技师"),
    THREE(3,"三级技师"),
    FOUR(4,"四级技师"),
    FIVE(5,"五级技师");

    @Getter
    int code;
    @Getter
    String name;

    LevelEnum(int code,String name){
        this.code = code;
        this.name = name;
    }

    public static LevelEnum valuesOf(int code){
        for(LevelEnum levelEnum:LevelEnum.values()){
            if (levelEnum.getCode()==code){
                return levelEnum;
            }
        }
        return null;
    }
}
