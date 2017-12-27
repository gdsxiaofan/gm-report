package com.huaxia.tongyong.service.impl;

import com.huaxia.tongyong.param.GroupParam;
import com.huaxia.tongyong.repository.GroupInfoMapper;
import com.huaxia.tongyong.service.GroupBiz;
import com.huaxia.tongyong.vo.GroupInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 获取班组信息
     * @param groupParam
     * @return
     */
    @Override
    public List<GroupInfoVo> getGroupInfoVoList(GroupParam groupParam) {
        return null;
    }

    /**
     * 新增班组信息
     * @param groupParam
     * @return
     */
    @Override
    public boolean addGroupInfo(GroupParam groupParam) {
        return false;
    }

    /**
     * 更新班组信息
     * @param groupParam
     * @return
     */
    @Override
    public boolean updateGroupInfo(GroupParam groupParam) {
        return false;
    }
}
