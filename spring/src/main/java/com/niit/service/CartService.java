package com.niit.service;

import java.io.IOException;

import com.niit.model.Cart;

public interface CartService {
	Cart getCartByCartId(int cartId);

	Cart validate(int cartId) throws IOException;

	void update(Cart cart);
}
