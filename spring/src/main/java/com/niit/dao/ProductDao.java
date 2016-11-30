package com.niit.dao;

import java.util.List;

import com.niit.model.Product;

public interface ProductDao {
	public List<Product> getAllProducts();

	Product getProductByProductId(int productId);

	void deleteProduct(int productId);

	void editProduct(Product product);

	void addProduct(Product product);
}
