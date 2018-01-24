package com.huaxia.tongyong.service.impl;

import com.huaxia.tongyong.model.DeviceInfo;
import com.huaxia.tongyong.param.DeviceInfoParam;
import com.huaxia.tongyong.repository.DeviceInfoMapper;
import com.huaxia.tongyong.service.DeviceInfoBiz;
import com.huaxia.tongyong.vo.DeviceInfoVo;
import io.jsonwebtoken.lang.Assert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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
        //1.转换设备新
        DeviceInfo deviceInfo = transferDeviceInfoParam(deviceInfoParam);
        //2.保存设备信息
        deviceInfo.setCreateTime(new Date());
        Integer count =deviceInfoMapper.insertSelective(deviceInfo);
        if(count.intValue()==0){
            throw new RuntimeException("保存设备信息失败");
        }
    }

    @Override
    public void updateDeviceInfo(DeviceInfoParam deviceInfoParam) {
        Assert.isTrue(deviceInfoParam.getId()!=null,"设备id不能为空");
        //1.转换设备新
        DeviceInfo deviceInfo = transferDeviceInfoParam(deviceInfoParam);
        //2.保存设备信息
        deviceInfo.setId(deviceInfoParam.getId());
        Integer count =deviceInfoMapper.updateByPrimaryKeySelective(deviceInfo);
        if(count.intValue()==0){
            throw new RuntimeException("保存设备信息失败");
        }
    }

    @Override
    public List<DeviceInfoVo> getDeviceList(String deviceName) {
        List<DeviceInfoVo> deviceInfoVos = deviceInfoMapper.getDeviceInfoList(deviceName);
        return deviceInfoVos;
    }

    @Override
    public DeviceInfoVo getDeviceDetail(Integer id) {

        DeviceInfoVo deviceInfoVo = deviceInfoMapper.selectByPrimaryKey(id);
        return deviceInfoVo;
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
