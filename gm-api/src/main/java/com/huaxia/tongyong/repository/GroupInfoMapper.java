package com.huaxia.tongyong.repository;

import com.huaxia.tongyong.model.GroupInfo;
import com.huaxia.tongyong.vo.GroupInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 
 * GroupInfoMapper数据库操作接口类
 * 
 **/
@Mapper
public interface GroupInfoMapper{

    /**
     * 保存班组信息
     * @param groupInfo
     * @return
     */
    public Integer insertSelective(GroupInfo groupInfo);

    /**
     * 查询班组信息
     * @param groupName
     * @return
     */
    public List<GroupInfoVo> selectGroupInfoVoList(
            @Param("groupName") String groupName
    );

    /**
     * 更新对应的分组信息
     * @param groupInfo
     * @return
     */
    public Integer updateByPrimaryKeySelective(GroupInfo groupInfo);

}