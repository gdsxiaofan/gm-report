package com.huaxia.tongyong.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 
 * 
 * 
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo{

	/****/
	private Long id;

	/**用户姓名**/
	private String name;

	/****/
	private String employeeNo;

	/**密码**/
	private String password;

	/**手机号码**/
	private String mobileNo;

	/**级别id**/
	private Long levelId;

	/**班组id**/
	private Long groupId;

	/**用户状态：1，启用 0，未启用**/
	private Integer userStatus;

	/**创建时间**/
	private java.util.Date createTime;

	/**更新时间**/
	private java.util.Date updateTime;

	/**有效状态：1，有效 0，无效**/
	private Integer deleteStatus;



	public void setId(Long id){
		this.id=id;
	}

	public Long getId(){
		return this.id;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return this.name;
	}

	public void setEmployeeNo(String employeeNo){
		this.employeeNo=employeeNo;
	}

	public String getEmployeeNo(){
		return this.employeeNo;
	}

	public void setPassword(String password){
		this.password=password;
	}

	public String getPassword(){
		return this.password;
	}

	public void setMobileNo(String mobileNo){
		this.mobileNo=mobileNo;
	}

	public String getMobileNo(){
		return this.mobileNo;
	}

	public void setLevelId(Long levelId){
		this.levelId=levelId;
	}

	public Long getLevelId(){
		return this.levelId;
	}

	public void setGroupId(Long groupId){
		this.groupId=groupId;
	}

	public Long getGroupId(){
		return this.groupId;
	}

	public void setUserStatus(Integer userStatus){
		this.userStatus=userStatus;
	}

	public Integer getUserStatus(){
		return this.userStatus;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime=createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime=updateTime;
	}

	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	public void setDeleteStatus(Integer deleteStatus){
		this.deleteStatus=deleteStatus;
	}

	public Integer getDeleteStatus(){
		return this.deleteStatus;
	}

}
