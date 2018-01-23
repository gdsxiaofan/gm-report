package com.huaxia.tongyong.param;

import lombok.Data;

/**
 * @Description:
 * @author: gongdaoshun
 * @Date: 2017/12/27 21:55
 * @className: UserParam
 */
@Data
public class UserParam {
    public String oldPassword;
    public String newPassword;

    public Long id;
    public String employeeNo;
    public String name;
    public String mobileNo;
    public Integer levelId;
    public Integer groupId;
}
