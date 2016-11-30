package com.niit.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Product;

@Repository("productDao")
public class ProductDaoImpl implements ProductDao {
	@Autowired
	private SessionFactory sessionFactory;

	// setter + getter
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// ALL THE METHODS

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Product> getAllProducts() {
		Session session = sessionFactory.openSession();
		List<Product> products = session.createQuery("FROM Product").list();
		return products;
	}

	@Transactional
	public Product getProductByProductId(int productId) {
		Session session = sessionFactory.openSession();
		Product product = (Product) session.get(Product.class, productId);
		session.flush();
		session.close();
		return product;
	}

	@Transactional
	public void deleteProduct(int productId) {
		Session session = sessionFactory.openSession();
		Product product = (Product) session.get(Product.class, productId);
		session.delete(product);
		session.flush();
		session.close();
	}

	@Transactional
	public void editProduct(Product product) {
		Session session = sessionFactory.openSession();
		session.update(product);
		session.flush();
		session.close();
	}

	@Transactional
	public void addProduct(Product product) {
		Session session = sessionFactory.openSession();
		session.save(product);
		session.flush();
		session.close();
	}

}
