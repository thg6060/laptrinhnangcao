package inventory.service;

import java.util.Date;
import java.util.List;
import inventory.dao.ProductInfoDAO;
import inventory.model.ProductInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inventory.dao.CategoryDAO;
import inventory.dao.ProductInStockDAO;
import inventory.model.Category;
import inventory.model.ProductInStock;

import java.util.HashMap;
import java.util.Map;
import org.springframework.util.StringUtils;
@Service
public class ProductService {
	@Autowired
	private CategoryDAO<Category> categoryDAO;
	@Autowired
	private ProductInfoDAO<ProductInfo> productInfoDAO;
	@Autowired
	private ProductInStockDAO<ProductInStock> productStockDAO;
	public void saveCategory(Category category) {
		category.setActiveFlag(1);
		category.setCreateDate(new Date());
		category.setUpdateDate(new Date());
		categoryDAO.save(category);
	}
	public void updateCategory(Category category) {
		category.setUpdateDate(new Date());
		categoryDAO.update(category);
	}
	public void deleteCategory(Category category) {
		category.setActiveFlag(0);
		category.setUpdateDate(new Date());
		categoryDAO.update(category);
	}
	public List<Category> findCategory(String property , Object value){
		return categoryDAO.findByProperty(property, value);
	}
	public List<Category> getAllCategory(Category category){
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashMap<>();
		if(category!=null) {
			if(category.getId()!=0) {
				queryStr.append(" and model.id=:id");
				mapParams.put("id", category.getId());
			}
		}
		return categoryDAO.findAll(queryStr.toString(), mapParams);
	}
	public Category findByIdCategory(int id) {
		return categoryDAO.findById(Category.class, id);
	}
	
	
	
	public void saveCategory(ProductInfo productInfo) {
		productInfo.setActiveFlag(1);
		productInfo.setCreateDate(new Date());
		productInfo.setUpdateDate(new Date());
		productInfoDAO.save(productInfo);
	}
	public void updateProductInfo(ProductInfo category) {
		category.setUpdateDate(new Date());
		productInfoDAO.update(category);
	}
	public void saveProductInfo(ProductInfo productInfo) {
		productInfo.setActiveFlag(1);
		productInfo.setCreateDate(new Date());
		productInfo.setUpdateDate(new Date());
		productInfoDAO.save(productInfo);
	}
	public void deleteProductInfo(ProductInfo category) {
		category.setActiveFlag(0);
		category.setUpdateDate(new Date());
		productInfoDAO.update(category);
	}
	public List<ProductInfo> findProductInfo(String property , Object value){
		return productInfoDAO.findByProperty(property, value);
	}
	public List<ProductInfo> getAllProductInfo(ProductInfo productInfo){
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashMap<>();
		if(productInfo!=null) {
			if(productInfo.getId()!=0) {
				queryStr.append(" and model.id=:id");
				mapParams.put("id", productInfo.getId());
			}
		}
		return productInfoDAO.findAll(queryStr.toString(), mapParams);
	}
	public ProductInfo findByIdProductInfo(int id) {
		return productInfoDAO.findById(ProductInfo.class, id);
	}
	
	public List<ProductInStock> getAllProductStock(ProductInStock productStock){
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashMap<>();
		if(productStock!=null) {
			if(productStock.getId()!=0) {
				queryStr.append(" and model.id=:id");
				mapParams.put("id", productStock.getId());
			}
		}
		return productStockDAO.findAllNotActiveFlag(queryStr.toString(), mapParams);
	}
	public void saveProductInStock(ProductInStock productStock) {
		productStock.setCreateDate(new Date());
		productStock.setUpdateDate(new Date());
		productStockDAO.updateProductStock(productStock);
	}
	public void exportProductInStock(ProductInStock productStock) {
		productStock.setCreateDate(new Date());
		productStock.setUpdateDate(new Date());
		productStockDAO.subtractionProductStock(productStock);
	}

}