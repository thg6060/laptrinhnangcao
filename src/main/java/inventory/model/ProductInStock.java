package inventory.model;
// Generated Dec 1, 2020, 11:28:51 AM by Hibernate Tools 5.2.12.Final

import java.util.Date;

/**
 * ProductInStock generated by hbm2java
 */
public class ProductInStock implements java.io.Serializable {

	private int id;
	private Long price;
	private Integer qty;
	private Integer productId;
	private Date createDate;
	private Date updateDate;

	public ProductInStock() {
	}

	public ProductInStock(int id) {
		this.id = id;
	}

	public ProductInStock(int id, Long price, Integer qty, Integer productId, Date createDate, Date updateDate) {
		this.id = id;
		this.price = price;
		this.qty = qty;
		this.productId = productId;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getPrice() {
		return this.price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getQty() {
		return this.qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
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