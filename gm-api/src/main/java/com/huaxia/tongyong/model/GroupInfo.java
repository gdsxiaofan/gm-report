package com.huaxia.tongyong.model;
import lombok.Data;


/**
 * 
 * 
 * 
 **/
@Data
public class GroupInfo{

	/****/
	private Long id;

	/**班组名称**/
	private String groupName;

	/**属性一**/
	private String propertyOne;

	/**属性二**/
	private String propertyTwo;

	/**属性三**/
	private String propertyThree;

	/**创建时间**/
	private java.util.Date createTime;

	/**更新时间**/
	private java.util.Date updateTime;

	/**有效状态：1，有效，0，无效**/
	private Integer deleteStatus;



	public void setId(Long id){
		this.id=id;
	}

	public Long getId(){
		return this.id;
	}

	public void setGroupName(String groupName){
		this.groupName=groupName;
	}

	public String getGroupName(){
		return this.groupName;
	}

	public void setPropertyOne(String propertyOne){
		this.propertyOne=propertyOne;
	}

	public String getPropertyOne(){
		return this.propertyOne;
	}

	public void setPropertyTwo(String propertyTwo){
		this.propertyTwo=propertyTwo;
	}

	public String getPropertyTwo(){
		return this.propertyTwo;
	}

	public void setPropertyThree(String propertyThree){
		this.propertyThree=propertyThree;
	}

	public String getPropertyThree(){
		return this.propertyThree;
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
