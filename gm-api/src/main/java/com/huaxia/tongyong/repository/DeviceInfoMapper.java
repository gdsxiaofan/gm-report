package com.huaxia.tongyong.repository;

import com.huaxia.tongyong.model.DeviceInfo;
import com.huaxia.tongyong.vo.DeviceInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 
 * DeviceInfoMapper数据库操作接口类
 * 
 **/

public interface DeviceInfoMapper{

    /**
     * 根据设备id查询设备信息
      * @param id
     * @return
     */
    DeviceInfoVo selectByPrimaryKey(int id);

    /**
     * 插入设备信息
     * @param deviceInfo
     * @return
     */
    public Integer insertSelective(DeviceInfo deviceInfo);

    /**
     * 根据id更新设备信息
     * @param deviceInfo
     * @return
     */
    public Integer updateByPrimaryKeySelective(DeviceInfo deviceInfo);

    /**
     * 查询设备信息
     * @param devcieName
     * @return
     */
    public List<DeviceInfoVo> getDeviceInfoList(
            @Param("deviceName")String devcieName);
}