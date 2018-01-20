package com.huaxia.tongyong.model;
import lombok.Data;


/**
 * 
 * 
 * 
 **/
@Data
public class ReportRepair{

	/****/
	private Long id;

	/**日报信息表id**/
	private Long reportId;

	/**维修保养设备**/
	private String deviceName;

	private Integer deviceId;

	/**维修保养设备**/
	private String repairComment;

	/**更换部件**/
	private String replaceParts;

	/**验证措施**/
	private String validateMethod;

	/**标定体积**/
	private String calibratedVolume;

	/**状态确认：1，未修复，2，已修复**/
	private Integer repairStatus;

	/**修复后首车号**/
	private String repairCarNo;

	/**创建时间**/
	private java.util.Date createTime;

	/**更新时间**/
	private java.util.Date updateTime;

	/**有效状态：1，有效 0，无效**/
	private Integer deleteStatus;

	private String repairUserName;


	public void setId(Long id){
		this.id=id;
	}

	public Long getId(){
		return this.id;
	}

	public void setReportId(Long reportId){
		this.reportId=reportId;
	}

	public Long getReportId(){
		return this.reportId;
	}

	public void setDeviceName(String deviceName){
		this.deviceName=deviceName;
	}

	public String getDeviceName(){
		return this.deviceName;
	}

	public void setRepairComment(String repairComment){
		this.repairComment=repairComment;
	}

	public String getRepairComment(){
		return this.repairComment;
	}

	public void setReplaceParts(String replaceParts){
		this.replaceParts=replaceParts;
	}

	public String getReplaceParts(){
		return this.replaceParts;
	}

	public void setValidateMethod(String validateMethod){
		this.validateMethod=validateMethod;
	}

	public String getValidateMethod(){
		return this.validateMethod;
	}

	public void setCalibratedVolume(String calibratedVolume){
		this.calibratedVolume=calibratedVolume;
	}

	public String getCalibratedVolume(){
		return this.calibratedVolume;
	}

	public void setRepairStatus(Integer repairStatus){
		this.repairStatus=repairStatus;
	}

	public Integer getRepairStatus(){
		return this.repairStatus;
	}

	public void setRepairCarNo(String repairCarNo){
		this.repairCarNo=repairCarNo;
	}

	public String getRepairCarNo(){
		return this.repairCarNo;
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
