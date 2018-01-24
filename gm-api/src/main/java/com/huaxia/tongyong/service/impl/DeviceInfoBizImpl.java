package com.huaxia.tongyong.service.impl;

import com.huaxia.tongyong.model.DeviceInfo;
import com.huaxia.tongyong.param.DeviceInfoParam;
import com.huaxia.tongyong.repository.DeviceInfoMapper;
import com.huaxia.tongyong.service.DeviceInfoBiz;
import com.huaxia.tongyong.vo.DeviceInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 设备信息相关业务代码
 *
 * @author gongdaoshun
 * @date 2018/1/24
 * @since 1.0.0
 */
@Service
public class DeviceInfoBizImpl implements DeviceInfoBiz{

    @Resource
    private DeviceInfoMapper deviceInfoMapper;

    @Override
    public void addDeviceInfo(DeviceInfoParam deviceInfoParam) {

    }

    @Override
    public void updateDeviceInfo(DeviceInfoParam deviceInfoParam) {

    }

    @Override
    public List<DeviceInfoVo> getDeviceList(String deviceName) {
        return null;
    }

    /**
     * 转换设备信息
     * @param deviceInfoParam
     * @return
     */
    private DeviceInfo transferDeviceInfoParam(DeviceInfoParam deviceInfoParam){
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceName(deviceInfoParam.getDeviceName());
        deviceInfo.setPropertyFour(deviceInfoParam.getPropertyFour());
        deviceInfo.setPropertyOne(deviceInfoParam.getPropertyOne());
        deviceInfo.setPropertyTwo(deviceInfoParam.getPropertyTwo());
        deviceInfo.setPropertyThree(deviceInfoParam.getPropertyThree());
        deviceInfo.setPropertyFour(deviceInfoParam.getPropertyFour());


        return deviceInfo;
    }
}
