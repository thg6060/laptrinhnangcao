package inventory.dao;

import java.util.Map;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import inventory.model.ProductInStock;

@Repository
@Transactional(rollbackFor = Exception.class)
public class BaseDAOImpl<E> implements BaseDAO<E> {
//	final static Logger log = Logger.getLogger(BaseDAOImpl.class);
	@Autowired
	SessionFactory sessionFactory;

	public List<E> findAll(String queryStr, Map<String, Object> mapParams) {
		StringBuilder queryString = new StringBuilder("");
		queryString.append(" from ").append(getGenericName()).append(" as model where model.activeFlag=1");
		if (queryStr != null && !queryStr.isEmpty()) {
			queryString.append(queryStr);
		}
		Query<E> query = sessionFactory.getCurrentSession().createQuery(queryString.toString());
		if (mapParams != null && !mapParams.isEmpty()) {
			for (String key : mapParams.keySet()) {
				query.setParameter(key, mapParams.get(key));
			}
		}
		List<E> r = query.list();
		return query.list();
	}

	public List<E> findAllNotActiveFlag(String queryStr, Map<String, Object> mapParams) {
		StringBuilder queryString = new StringBuilder("");
		queryString.append(" from ").append(getGenericName()).append(" as model");
		if (queryStr != null && !queryStr.isEmpty()) {
			queryString.append(queryStr);
		}
		Query<E> query = sessionFactory.getCurrentSession().createQuery(queryString.toString());
		if (mapParams != null && !mapParams.isEmpty()) {
			for (String key : mapParams.keySet()) {
				query.setParameter(key, mapParams.get(key));
			}
		}
		List<E> r = query.list();
		return query.list();
	}

	public E findById(Class<E> e, Serializable id) {
//		log.info("Find by ID ");
		return sessionFactory.getCurrentSession().get(e, id);
	}

	public List<E> findByProperty(String property, Object value) {
		StringBuilder queryString = new StringBuilder();
		queryString.append(" from ").append(getGenericName()).append(" as model where model.activeFlag=1 and model.")
				.append(property).append("=?");
		Query<E> query = sessionFactory.getCurrentSession().createQuery(queryString.toString());
		query.setParameter(0, value);
		return query.getResultList();

	}

	public void save(E instance) {
//		log.info(" save instance");
		sessionFactory.getCurrentSession().persist(instance);
	}

	@Override
	public void updateProductStock(ProductInStock productInStock) {
		StringBuilder checkQuery = new StringBuilder();
		checkQuery.append(" from ").append(getGenericName())
				.append(" as model where model.productId = " + productInStock.getProductId());
		Query<E> queryCheck = sessionFactory.getCurrentSession().createQuery(checkQuery.toString());
		List<E> check = queryCheck.getResultList();
		if (check.isEmpty()) {
			ProductInStock newInstance = new ProductInStock();
			newInstance.setCreateDate(new Date());
			newInstance.setPrice(10000L);
			newInstance.setProductId(productInStock.getProductId());
			newInstance.setQty(productInStock.getQty());
			newInstance.setUpdateDate(new Date());
			sessionFactory.getCurrentSession().merge(newInstance);
		} else {
			Session session = sessionFactory.getCurrentSession();
			session = sessionFactory.openSession();
			session.beginTransaction();
			SQLQuery sqlQuery = session.createSQLQuery(" update Product_In_Stock as model set model.qty = model.qty + "+productInStock.getQty()+" where model.product_id = "+productInStock.getProductId());
			int result = sqlQuery.executeUpdate();
			System.out.println(result);
			session.getTransaction().commit();
		}

	}
	@Override
	public void subtractionProductStock(ProductInStock productInStock) {
		StringBuilder checkQuery = new StringBuilder();
		checkQuery.append(" from ").append(getGenericName())
				.append(" as model where model.productId = " + productInStock.getProductId());
		Query<E> queryCheck = sessionFactory.getCurrentSession().createQuery(checkQuery.toString());
		List<E> check = queryCheck.getResultList();
		if (check.isEmpty()) {
			return;
		} else {
			Session session = sessionFactory.getCurrentSession();
			session = sessionFactory.openSession();
			session.beginTransaction();
			SQLQuery sqlQuery = session.createSQLQuery(" update Product_In_Stock as model set model.qty = model.qty - "+productInStock.getQty()+" where model.product_id = "+productInStock.getProductId());
			int result = sqlQuery.executeUpdate();
			System.out.println(result);
			session.getTransaction().commit();
		}

	}
	public void update(E instance) {
//		log.info("update");
		sessionFactory.getCurrentSession().merge(instance);
	}

	//
	public String getGenericName() {
		String s = getClass().getGenericSuperclass().toString();
		Pattern pattern = Pattern.compile("\\<(.*?)\\>");
		Matcher m = pattern.matcher(s);
		String generic = "null";
		if (m.find()) {
			generic = m.group(1);
		}
		return generic;
	}

}