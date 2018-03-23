package com.huaxia.tongyong.model;
import lombok.Data;


/**
 * 
 * 
 * 
 **/
@Data
public class ReportFault{

	/****/
	private Long id;

	/**关联的日报信息表id**/
	private Long reportId;

	/**设备名称**/
	private String deviceName;

	private Integer deviceId;

	/**设备故障**/
	private String deviceFault;

	/**处理方法**/
	private String dealMethod;

	/**区域，关联字典表id**/
	private Integer areaId;

	private String areaName;

	/**停线时间**/
	private String stopTime;

	/**
	 * 停线时长
	 */
	private String stopTimeSpan;

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

	public void setDeviceFault(String deviceFault){
		this.deviceFault=deviceFault;
	}

	public String getDeviceFault(){
		return this.deviceFault;
	}

	public void setDealMethod(String dealMethod){
		this.dealMethod=dealMethod;
	}

	public String getDealMethod(){
		return this.dealMethod;
	}

	public void setAreaId(Integer areaId){
		this.areaId=areaId;
	}

	public Integer getAreaId(){
		return this.areaId;
	}

	public void setStopTime(String stopTime){
		this.stopTime=stopTime;
	}

	public String getStopTime(){
		return this.stopTime;
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
