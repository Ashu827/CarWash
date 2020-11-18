package com.carwash.customer.service;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.carwash.customer.model.Customer;
import com.carwash.customer.repository.CustomerRepository;
import com.carwash.customer.service.CustomerService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerServiceTest {

	@Mock
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerService customerService;
	
	Customer customer = new Customer("ashu2@gmail.com", "ashu", "ashu","added");
	
	@Test
	void addNewCustomerTest() {
		Mockito.when(customerRepository.save(customer)).thenReturn(customer);
		assertThat(customerService.addNewCustomer(customer)).isEqualTo(customer);
	}
}
