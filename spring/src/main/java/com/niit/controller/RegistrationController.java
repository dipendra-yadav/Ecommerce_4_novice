package com.niit.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.model.BillingAddress;
import com.niit.model.Customer;
import com.niit.model.ShippingAddress;
import com.niit.model.Users;
import com.niit.service.CustomerServices;

@Controller
public class RegistrationController {
	@Autowired
	private CustomerServices customerServices;

	// setter + getter
	public CustomerServices getCustomerServices() {
		return customerServices;
	}

	public void setCustomerServices(CustomerServices customerServices) {
		this.customerServices = customerServices;
	}

	// To display registration form
	@RequestMapping("/customer/registration")
	public ModelAndView getRegistrationForm() {
		Customer customer = new Customer();
		Users users = new Users();
		BillingAddress billingAddress = new BillingAddress();
		ShippingAddress shippingAddress = new ShippingAddress();

		customer.setUsers(users);
		customer.setBillingAddress(billingAddress);
		customer.setShippingAddress(shippingAddress);

		return new ModelAndView("registrationCustomer", "customer", customer);

	}

	// to insert the data
	@RequestMapping(value = "/customer/registration", method = RequestMethod.POST)
	public String registerCustomer(@Valid @ModelAttribute(value = "customer") Customer customer, BindingResult result,
			Model model) {
		if (result.hasErrors())
			return "registrationCustomer";

		List<Customer> customerList = customerServices.getAllCustomers();
		for (Customer c : customerList) {
			if (c.getUsers().getUsername().equals(customer.getUsers().getUsername())) {
				model.addAttribute("duplicateUname", "Username already exists");
				return "registrationCustomer";
			}
			if (c.getCustomerEmail().equals(customer.getCustomerEmail())) {
				model.addAttribute("duplicateEmail", "Email Id is invalid or already exists!");
				return "registrationCustomer";
			}
		}

		customerServices.addCustomer(customer);
		model.addAttribute("registration success", "Registered Successfully. Login using username and password");
		return "login";
	}

}
