package com.carwash.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.carwash.customer.model.Customer;
import com.carwash.customer.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	PasswordEncoder bCrypt;

	public Customer addNewCustomer(Customer customer) {

		if (customerRepository.existsById(customer.getEmailId())) {
			return null;
		}
		else {
			String encodedPassword = bCrypt.encode(customer.getPassword());
			customer.setPassword(encodedPassword);
			customerRepository.save(customer);
			return customer;
		}

	}

	public Optional<Customer> login(Customer customer)  {
		if(customer.getEmailId()!=null) {
		Optional<Customer> cred = customerRepository.findById(customer.getEmailId());
		boolean data= customerRepository.existsById(customer.getEmailId());
			if(data) {
				if(bCrypt.matches(customer.getPassword(), cred.get().getPassword())){
					return cred;
				}
				else {
					return null;
				}
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}
		
	
	public List<Customer> getAllUser() {
		return customerRepository.findAll();
	}
}
