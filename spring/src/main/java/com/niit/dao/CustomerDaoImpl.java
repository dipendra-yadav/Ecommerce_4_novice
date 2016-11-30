package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Authorities;
import com.niit.model.Cart;
import com.niit.model.Customer;
import com.niit.model.Users;

@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao {
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

	public void addCustomer(Customer customer) {
		Session session = sessionFactory.openSession();

		customer.getUsers().setEnabled(true);

		Authorities authorities = new Authorities();
		String username = customer.getUsers().getUsername();
		authorities.setUsername(username);
		authorities.setAuthority("ROLE_USER");

		Cart cart = new Cart();
		customer.setCart(cart);
		cart.setCustomer(customer);

		session.save(customer);
		session.save(authorities);
		session.flush();
		session.close();
	}

	public List<Customer> getAllCustomers() {
		Session session = sessionFactory.openSession();
		List<Customer> customers = session.createQuery("from Customer").list();
		return customers;
	}

	public Customer getCustomerByUsername(String username) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Users where username=?");
		query.setString(0, username);
		Users users = (Users) query.uniqueResult();
		Customer customer = users.getCustomer();
		return customer;
	}
}
