package com.niit.dao;

import java.util.List;

import com.niit.model.Customer;

public interface CustomerDao {
	void addCustomer(Customer customer);

	List<Customer> getAllCustomers();

	public Customer getCustomerByUsername(String username);
}
