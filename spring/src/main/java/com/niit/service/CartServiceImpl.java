package com.niit.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.dao.CartDao;
import com.niit.model.Cart;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartDao cartDao;

	// setter + getter
	public CartDao getCartDao() {
		return cartDao;
	}

	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}

	// all the methods
	public Cart getCartByCartId(int cartId) {
		return cartDao.getCartByCartId(cartId);
	}

	public Cart validate(int cartId) throws IOException {
		return cartDao.validate(cartId);
	}

	public void update(Cart cart) {
		cartDao.update(cart);
	}
}
