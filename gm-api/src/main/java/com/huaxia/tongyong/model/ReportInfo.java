package com.huaxia.tongyong.model;
import lombok.Data;


/**
 * 
 * 日报信息表
 * 
 **/
@Data
public class ReportInfo{

	/****/
	private Long id;

	private String reportName;

	/**日报类型：1，故障 2，修复验证**/
	private Integer reportType;

	/**日报状态：1，已保存 2，已提交**/
	private Integer reportStatus;

	/**创建人姓名**/
	private String createUserName;

	/**创建人id**/
	private Long createUserId;

	/**负责人姓名**/
	private String chargeUserName;

	/**负责人id**/
	private Long chargeUserId;

	/**创建时间**/
	private java.util.Date createTime;

	/**更新时间**/
	private java.util.Date updateTime;

	/**有效状态：1，有效 0 无效**/
	private Integer deleteStatus;

	/**已读状态：1，已读 0 未读**/
	private Integer readStatus;



	public void setId(Long id){
		this.id=id;
	}

	public Long getId(){
		return this.id;
	}

	public void setReportType(Integer reportType){
		this.reportType=reportType;
	}

	public Integer getReportType(){
		return this.reportType;
	}

	public void setReportStatus(Integer reportStatus){
		this.reportStatus=reportStatus;
	}

	public Integer getReportStatus(){
		return this.reportStatus;
	}

	public void setCreateUserName(String createUserName){
		this.createUserName=createUserName;
	}

	public String getCreateUserName(){
		return this.createUserName;
	}

	public void setCreateUserId(Long createUserId){
		this.createUserId=createUserId;
	}

	public Long getCreateUserId(){
		return this.createUserId;
	}

	public void setChargeUserName(String chargeUserName){
		this.chargeUserName=chargeUserName;
	}

	public String getChargeUserName(){
		return this.chargeUserName;
	}

	public void setChargeUserId(Long chargeUserId){
		this.chargeUserId=chargeUserId;
	}

	public Long getChargeUserId(){
		return this.chargeUserId;
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
