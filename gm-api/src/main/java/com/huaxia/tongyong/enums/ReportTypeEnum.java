package com.huaxia.tongyong.enums;

import lombok.Getter;

/**
 * @Description: 日报类型
 * @author: gongdaoshun
 * @Date: 2017/12/13 22:03
 * @className: ReportTypeEnum
 */
public enum ReportTypeEnum {
    FAULT(1,"故障"),
    REPAIR(2,"修复验正");

    @Getter
    int code;
    @Getter
    String name;

    ReportTypeEnum(int code,String name){
        this.code = code;
        this.name = name;
    }

    public static ReportTypeEnum valuesOf(int code){
        for(ReportTypeEnum reportTypeEnum :ReportTypeEnum.values()){
            if(reportTypeEnum.getCode()==code){
                return reportTypeEnum;
            }
        }
        return null;
    }
}
