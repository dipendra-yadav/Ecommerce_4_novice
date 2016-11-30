package com.niit.service;

import java.util.List;

import com.niit.model.Product;

public interface ProductService {
	List<Product> getAllProducts();

	Product getProductByProductId(int productId);

	void deleteProduct(int productId);

	void editProduct(Product product);

	void addProduct(Product product);
}
