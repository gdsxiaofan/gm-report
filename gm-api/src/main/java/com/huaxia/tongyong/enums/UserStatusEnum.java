package com.huaxia.tongyong.enums;

import lombok.Getter;

/**
 * @Description:
 * @author: gongdaoshun
 * @Date: 2017/12/28 20:22
 * @className: UserStatusEnum
 */
public enum UserStatusEnum {
    USING(1,"启用"),
    SUSPEND(0,"暂停");

    @Getter
    int code;
    @Getter
    String name;

    UserStatusEnum(int code,String name){
        this.code = code;
        this.name = name;
    }

    public static UserStatusEnum valuesOf(int code){
        for(UserStatusEnum userStatusEnum:UserStatusEnum.values()){
            if(userStatusEnum.getCode()==code){
                return userStatusEnum;
            }
        }
        return null;
    }
}