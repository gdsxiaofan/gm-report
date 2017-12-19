package com.huaxia.tongyong.model;
import lombok.Data;


/**
 * 
 * 字典信息表
 * 
 **/
@Data
public class DictionaryInfo{

	/****/
	private Integer id;

	/**字典类型：1，设备，2，区域**/
	private Integer dictionaryType;

	/**字典名称**/
	private String dictionaryName;

	/**创建时间**/
	private java.util.Date createTime;

	/**更新时间**/
	private java.util.Date updateTime;

	/**有效状态：1，有效 0，无效**/
	private Integer deleteStatus;



	public void setId(Integer id){
		this.id=id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setDictionaryType(Integer dictionaryType){
		this.dictionaryType=dictionaryType;
	}

	public Integer getDictionaryType(){
		return this.dictionaryType;
	}

	public void setDictionaryName(String dictionaryName){
		this.dictionaryName=dictionaryName;
	}

	public String getDictionaryName(){
		return this.dictionaryName;
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
