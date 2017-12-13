package com.huaxia.tongyong.enums;


import lombok.Getter;

import javax.print.DocFlavor;

/**
 * @Description: 日报状态枚举
 * @author: gongdaoshun
 * @Date: 2017/12/13 23:02
 * @className: ReportStatusEnum
 */
public enum ReportStatusEnum {
    SAVE(1,"已保存"),
    SUBMIT(2,"已提交");

    @Getter
    int code;
    @Getter
    String name;

    ReportStatusEnum(int code,String name){
        this.code = code;
        this.name = name;
    }

    public static ReportStatusEnum valuesOf(int code){
        for(ReportStatusEnum reportStatusEnum: ReportStatusEnum.values()){
            if(reportStatusEnum.code==code){
                return reportStatusEnum;
            }
        }
        return null;
    }
}