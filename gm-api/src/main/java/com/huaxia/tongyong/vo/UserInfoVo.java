package com.huaxia.tongyong.vo;

/**
 * @Description: 用户信息
 * @author: gongdaoshun
 * @Date: 2017/12/14 18:46
 * @className: UserInfoVo
 */
public class UserInfoVo {
    /****/
    private Long id;

    /**用户姓名**/
    private String name;

    /****/
    private String employeeNo;

    /**手机号码**/
    private String mobileNo;

    /**级别id**/
    private Long levelId;

    /**班组id**/
    private Long groupId;

    /**班组名称**/
    private Long groupName;

    /**用户状态：1，启用 0，未启用**/
    private Integer userStatus;
}
