package com.niit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.model.Cart;
import com.niit.model.Customer;
import com.niit.model.CustomerOrder;
import com.niit.service.CartService;
import com.niit.service.CustomerOrderService;

@Controller
public class OrderController {
	@Autowired
	private CartService cartService;

	@Autowired
	private CustomerOrderService customerOrderService;

	// setters + getters
	public CartService getCartService() {
		return cartService;
	}

	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

	public CustomerOrderService getCustomerOrderService() {
		return customerOrderService;
	}

	public void setCustomerOrderService(CustomerOrderService customerOrderService) {
		this.customerOrderService = customerOrderService;
	}

	@RequestMapping(value = "/order/{cartId}")
	public String createOrder(@PathVariable("cartId") int cartId) {

		System.out.println("1111111");
		CustomerOrder customerOrder = new CustomerOrder();
		Cart cart = cartService.getCartByCartId(cartId);
		// UPDATE CARTID FOR CUSTOMERORDER - SET CARTID
		customerOrder.setCart(cart);
		System.out.println("2");

		Customer customer = cart.getCustomer();
		// SET CUSTOMERID
		customerOrder.setCustomer(customer);
		System.out.println("3");
		// SET BILLINGADDRESSID
		customerOrder.setBillingAddress(customer.getBillingAddress());
		System.out.println("4");
		// SET SHIPPINGADDRESSID
		customerOrder.setShippingAddress(customer.getShippingAddress());
		System.out.println("5");

		customerOrderService.addCustomerOrder(customerOrder);
		System.out.println("6");

		return "redirect:/checkout?cartId=" + cartId;

	}

}
