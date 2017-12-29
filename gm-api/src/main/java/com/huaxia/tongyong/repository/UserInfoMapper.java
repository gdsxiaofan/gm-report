package com.huaxia.tongyong.repository;

import com.huaxia.tongyong.model.UserInfo;
import com.huaxia.tongyong.vo.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 
 * UserInfoMapper数据库操作接口类
 * 
 **/
@Mapper
public interface UserInfoMapper{


    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    public UserInfo selectByPrimaryKey(
            @Param("id")Long id);

    /**
     * 用于用户登陆时，查询密码所用
     * @param employeeNo
     * @param password
     * @return
     */
    public UserInfo selectUserInfoForLogin(
            @Param("employeeNo")String employeeNo,
            @Param("password")String password
    );

    /**
     * 新增用户信息
     * @param userInfo
     * @return
     */
    public Integer insertSelective(UserInfo userInfo);


    /**
     * 更新用户信息
     * @param userInfo
     * @return
     */
    public Integer updateUser(UserInfo userInfo);

    /**
     * 查询用户列表信息
     * @param employeeNo
     * @param userName
     * @return
     */
    public List<UserInfo> selectUserInfoByNoAndName(
            @Param("employeeNo")String employeeNo,
            @Param("userName")String userName
    );

    /**
     * 查询用户列表信息
     * @param employeeNo
     * @param userName
     * @return
     */
    public List<UserInfoVo> selectUserInfoVo(
            @Param("employeeNo")String employeeNo,
            @Param("userName")String userName
    );

}