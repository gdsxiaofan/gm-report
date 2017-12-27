package com.huaxia.tongyong.param;

import lombok.Data;

/**
 * @Description: 班组管理交互参数定义
 * @author: gongdaoshun
 * @Date: 2017/12/27 22:07
 * @className: GroupParam
 */
@Data
public class GroupParam extends BaseQueryParams{

    private Long id;

    private String groupName;

    private String propertyOne;

    /**属性二**/
    private String propertyTwo;

    /**属性三**/
    private String propertyThree;
}
