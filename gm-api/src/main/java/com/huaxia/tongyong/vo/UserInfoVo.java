package com.huaxia.tongyong.vo;

import com.huaxia.tongyong.enums.LevelEnum;
import com.huaxia.tongyong.enums.UserStatusEnum;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description: 用户信息
 * @author: gongdaoshun
 * @Date: 2017/12/14 18:46
 * @className: UserInfoVo
 */
@Data
public class UserInfoVo {
    /****/
    private Long id;

    /**用户姓名**/
    private String name;

    /****/
    private String employeeNo;

    /**手机号码**/
    private String mobileNo;

    /**级别id**/
    private Integer levelId;

    /**
     * 级别描述
     */
    private String levelName;

    /**班组id**/
    private Long groupId;

    /**班组名称**/
    private String groupName;

    /**用户状态：1，启用 0，未启用**/
    private Integer userStatus;

    /**
     * 用户状态描述
     */
    private String userStatusName;

    public void setUserStatus(Integer userStatus){
        this.userStatus = userStatus;
        this.userStatusName = UserStatusEnum.valuesOf(userStatus).getName();
    }

    public void setUserStatusName(String userStatusName){
        if(StringUtils.isBlank(this.userStatusName)){
            this.userStatusName = userStatusName;
        }
    }

    public void setLevelId(Integer levelId){
        this.levelId = levelId;
        this.levelName = LevelEnum.valuesOf(levelId).getName();
    }

    public void setLevelName(String levelName){
        if(StringUtils.isBlank(levelName)){
            this.levelName = levelName;
        }
    }
}
