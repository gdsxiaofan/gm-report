package com.huaxia.tongyong.param;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinitions;

/**
 * @Description:
 * @author: gongdaoshun
 * @Date: 2017/12/27 22:00
 * @className: UserQueryParam
 */
@Data
public class UserQueryParam extends BaseQueryParams{
    private String employeeNo;
    private String userName;
}
