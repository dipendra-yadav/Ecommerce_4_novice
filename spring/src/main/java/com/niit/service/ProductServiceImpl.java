package com.niit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.dao.ProductDao;
import com.niit.model.Product;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao productDao;

	// setter + getter
	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	// All the methods
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

	public Product getProductByProductId(int productId) {
		return productDao.getProductByProductId(productId);
	}

	public void deleteProduct(int productId) {
		productDao.deleteProduct(productId);
	}

	public void editProduct(Product product) {
		productDao.editProduct(product);
	}

	public void addProduct(Product product) {
		productDao.addProduct(product);
	}

}
