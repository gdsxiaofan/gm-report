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
	private String workHours;

	/**属性一**/
	private String propertyOne;

	/**属性二**/
	private String propertyTwo;

	/**创建时间**/
	private java.util.Date createTime;

	/**更新时间**/
	private java.util.Date updateTime;

}
