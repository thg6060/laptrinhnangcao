package inventory.model;
// Generated Dec 1, 2020, 11:28:51 AM by Hibernate Tools 5.2.12.Final

import java.util.Date;

/**
 * UserRole generated by hbm2java
 */
public class UserRole implements java.io.Serializable {

	private int id;
	private Integer userId;
	private Integer roleId;
	private Integer activeFlag;
	private Date createDate;
	private Date updateDate;

	public UserRole() {
	}

	public UserRole(int id) {
		this.id = id;
	}

	public UserRole(int id, Integer userId, Integer roleId, Integer activeFlag, Date createDate, Date updateDate) {
		this.id = id;
		this.userId = userId;
		this.roleId = roleId;
		this.activeFlag = activeFlag;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}