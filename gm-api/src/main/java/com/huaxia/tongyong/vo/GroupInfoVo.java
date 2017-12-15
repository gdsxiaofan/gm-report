package com.huaxia.tongyong.vo;

import lombok.Data;

/**
 * @Description: 分组信息
 * @author: gongdaoshun
 * @Date: 2017/12/14 18:46
 * @className: GroupInfoVo
 */
@Data
public class GroupInfoVo {
    /****/
    private Long id;

    /**班组名称**/
    private String groupName;

    /**属性一**/
    private String propertyOne;

    /**属性二**/
    private String propertyTwo;

    /**属性三**/
    private String propertyThree;
}
