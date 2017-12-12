package com.huaxia.tongyong.model;
import lombok.Data;


/**
 * 
 * 
 * 
 **/
@Data
public class ReportTransferLog{

	/****/
	private Long id;

	/**日报信息id**/
	private Long reportId;

	/**移交前负责人id**/
	private Long beforeUserId;

	/**移交前人姓名**/
	private String beforeUserName;

	/**移交后人id**/
	private Long afterUserId;

	/**移交后人姓名**/
	private String afterUserName;

	/**创建时间**/
	private java.util.Date createTime;



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

	public void setBeforeUserId(Long beforeUserId){
		this.beforeUserId=beforeUserId;
	}

	public Long getBeforeUserId(){
		return this.beforeUserId;
	}

	public void setBeforeUserName(String beforeUserName){
		this.beforeUserName=beforeUserName;
	}

	public String getBeforeUserName(){
		return this.beforeUserName;
	}

	public void setAfterUserId(Long afterUserId){
		this.afterUserId=afterUserId;
	}

	public Long getAfterUserId(){
		return this.afterUserId;
	}

	public void setAfterUserName(String afterUserName){
		this.afterUserName=afterUserName;
	}

	public String getAfterUserName(){
		return this.afterUserName;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime=createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

}
