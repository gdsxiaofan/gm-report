package com.huaxia.tongyong.service;

import com.huaxia.tongyong.param.DeviceInfoParam;
import com.huaxia.tongyong.vo.DeviceInfoVo;

import java.util.List;

/**
 * TODO: 请添加描述
 *
 * @author gongdaoshun
 * @date 2018/1/23
 * @since 1.0.0
 */
public interface DeviceInfoBiz {

    public void addDeviceInfo(DeviceInfoParam deviceInfoParam);

    public void updateDeviceInfo(DeviceInfoParam deviceInfoParam);

    public List<DeviceInfoVo> getDeviceList(String deviceName);
}
