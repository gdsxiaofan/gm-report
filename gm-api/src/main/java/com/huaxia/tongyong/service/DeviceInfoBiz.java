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

    /**
     * 新增设备信息
     * @param deviceInfoParam
     */
    public void addDeviceInfo(DeviceInfoParam deviceInfoParam);

    /**
     * 更新设备信息
     * @param deviceInfoParam
     */
    public void updateDeviceInfo(DeviceInfoParam deviceInfoParam);

    /**
     * 查询设备列表
     * @param deviceName
     * @return
     */
    public List<DeviceInfoVo> getDeviceList(String deviceName);

    /**
     * 查询设备详细信息
     * @param id
     * @return
     */
    public DeviceInfoVo getDeviceDetail(Integer id);

    /**
     * 删除设备
     * @param id
     */
    void delDeviceInfo(Integer id);
}
