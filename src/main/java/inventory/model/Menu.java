package inventory.model;
// Generated Dec 1, 2020, 11:28:51 AM by Hibernate Tools 5.2.12.Final

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Menu generated by hbm2java
 */
public class Menu implements java.io.Serializable {

	private int id;
	private Integer parentId;
	private String url;
	private Integer activeFlag;
	private Date createDate;
	private Date updateDate;
	private Integer orderIndex;
	private String name;
	private List<Menu> child;
	private String idMenu;

	public Menu() {
	}

	public Menu(int id) {
		this.id = id;
	}

	public Menu(int id, Integer parentId, String url, Integer activeFlag, Date createDate, Date updateDate,
			Integer orderIndex, String name) {
		this.id = id;
		this.parentId = parentId;
		this.url = url;
		this.activeFlag = activeFlag;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.orderIndex = orderIndex;
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public Integer getOrderIndex() {
		return this.orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public List<Menu> getChild() {
		return child;
	}

	public void setChild(List<Menu> child) {
		this.child = child;
	}

	public String getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(String idMenu) {
		this.idMenu = idMenu;
	}

}
