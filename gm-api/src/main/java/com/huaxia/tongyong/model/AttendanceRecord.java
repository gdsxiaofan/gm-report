package com.huaxia.tongyong.model;
import lombok.Data;


/**
 * 
 * 
 * 
 **/
@Data
public class AttendanceRecord{

	/****/
	private Integer id;

	/**工号**/
	private String employeeNo;

	/**用户姓名**/
	private String userName;

	/**上班时间**/
	private java.util.Date workStartTime;

	/**下班时间**/
	private java.util.Date workEndTime;

	/**工作时长**/
	private Integer workHours;

	/**属性一**/
	private String propertyOne;

	/**属性二**/
	private String propertyTwo;

	/**创建时间**/
	private java.util.Date createTime;

	/**更新时间**/
	private java.util.Date updateTime;



	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setEmployeeNo(String employeeNo){
		this.employeeNo=employeeNo;
	}

	public String getEmployeeNo(){
		return this.employeeNo;
	}

	public void setUserName(String userName){
		this.userName=userName;
	}

	public String getUserName(){
		return this.userName;
	}

	public void setWorkStartTime(java.util.Date workStartTime){
		this.workStartTime=workStartTime;
	}

	public java.util.Date getWorkStartTime(){
		return this.workStartTime;
	}

	public void setWorkEndTime(java.util.Date workEndTime){
		this.workEndTime=workEndTime;
	}

	public java.util.Date getWorkEndTime(){
		return this.workEndTime;
	}

	public void setWorkHours(Integer workHours){
		this.workHours=workHours;
	}

	public Integer getWorkHours(){
		return this.workHours;
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

}
