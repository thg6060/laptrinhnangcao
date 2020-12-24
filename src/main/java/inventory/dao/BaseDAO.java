package inventory.dao;
import java.util.Map;

import inventory.model.ProductInStock;

import java.io.Serializable;
import java.util.List;

public interface BaseDAO<E> {
	public List<E> findAll(String queryStr, Map<String, Object> mapParams);
	
	public List<E> findAllNotActiveFlag(String queryStr, Map<String, Object> mapParams);

	public E findById(Class<E> e, Serializable id);

	public List<E> findByProperty(String property, Object value);

	public void save(E instance);
	
	public void updateProductStock(ProductInStock productInStock);
	
	public void subtractionProductStock(ProductInStock productInStock);

	public void update(E instance);
}
