package com.huaxia.tongyong.param;

import lombok.Data;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2018/1/22
 * @since 1.0.0
 */
@Data
public class DeviceInfoParam extends BaseQueryParams{
    /****/
    private Integer id;

    /**设备名称**/
    private String deviceName;

    /**属性一**/
    private String propertyOne;

    /**属性二**/
    private String propertyTwo;

    /**属性三**/
    private String propertyThree;

    /**属性四**/
    private String propertyFour;
}
