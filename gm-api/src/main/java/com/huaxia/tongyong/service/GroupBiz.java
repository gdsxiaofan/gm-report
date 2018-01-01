package com.huaxia.tongyong.service;

import com.huaxia.tongyong.param.GroupParam;
import com.huaxia.tongyong.vo.GroupInfoVo;

import java.util.List;

/**
 * @Description: 班组相关的接口
 * @author: gongdaoshun
 * @Date: 2017/12/27 22:20
 * @className: GroupBiz
 */
public interface GroupBiz {

    /**
     * 查询班组列表信息
     * @param groupParam
     * @return
     */
    public List<GroupInfoVo> getGroupInfoVoList(GroupParam groupParam);

    /**
     * 新增班组信息
     * @param groupParam
     * @return
     */
    public boolean addGroupInfo(GroupParam groupParam);

    /**
     * 更新班组信息
     * @param groupParam
     * @return
     */
    public boolean updateGroupInfo(GroupParam groupParam);

    boolean delGroupInfo(Long id);
}