package com.huaxia.tongyong.service.impl;

import com.github.pagehelper.PageHelper;
import com.huaxia.tongyong.model.GroupInfo;
import com.huaxia.tongyong.param.GroupParam;
import com.huaxia.tongyong.repository.GroupInfoMapper;
import com.huaxia.tongyong.service.GroupBiz;
import com.huaxia.tongyong.vo.GroupInfoVo;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @author: gongdaoshun
 * @Date: 2017/12/27 22:29
 * @className: GroupBizImpl
 */
@Service
public class GroupBizImpl implements GroupBiz {

    @Autowired
    private GroupInfoMapper groupInfoMapper;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    /**
     * 获取班组信息
     * @param groupParam
     * @return
     */
    @Override
    public List<GroupInfoVo> getGroupInfoVoList(GroupParam groupParam) {
        PageHelper.startPage(groupParam.getPageNum(),groupParam.getPageSize());
        List<GroupInfoVo> groupInfoVos = groupInfoMapper.selectGroupInfoVoList(groupParam.getGroupName());
        return groupInfoVos;
    }

    /**
     * 新增班组信息
     * @param groupParam
     * @return
     */
    @Override
    public boolean addGroupInfo(GroupParam groupParam) {
        GroupInfo groupInfo = transferGroupInfo(groupParam);
        groupInfo.setCreateTime(new Date());
        Integer count = groupInfoMapper.insertSelective(groupInfo);
        return count.intValue()==1?true:false;
    }

    /**
     * 更新班组信息
     * @param groupParam
     * @return
     */
    @Override
    public boolean updateGroupInfo(GroupParam groupParam) {

        GroupInfo groupInfo = transferGroupInfo(groupParam);

        Integer count = groupInfoMapper.updateByPrimaryKeySelective(groupInfo);
        return count.intValue()==1?true:false;
    }

    /**
     * 转换集团信息
     * @param groupParam
     * @return
     */
    private GroupInfo transferGroupInfo(GroupParam groupParam){
        GroupInfo groupInfo = dozerBeanMapper.map(groupParam,GroupInfo.class);
        return groupInfo;
    }
}
