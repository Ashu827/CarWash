package com.carwash.customer.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.carwash.customer.model.CarDetails;
import com.carwash.customer.model.Customer;
import com.carwash.customer.service.CarService;
import com.carwash.customer.service.CustomerService;
import com.carwash.customer.service.RabbitMQSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CustomerController.class)
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	@MockBean
	private CarService carService;

	@MockBean
	RabbitMQSender rabbbit;

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

	@Test
	public void testRegister() throws Exception {
		Customer customer = new Customer("ashu@gmail.com", "ashu", "ashu","added");
		String inputJson = this.mapToJson(customer);

		String URI = "/customer/registration";

		Mockito.when(customerService.addNewCustomer(Mockito.any(Customer.class))).thenReturn(customer);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputJson).contentType(MediaType.APPLICATION_JSON);
	
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse httpServletResponse = result.getResponse();

		String outputJson = httpServletResponse.getContentAsString();

		assertThat(outputJson).isEqualTo(inputJson);
		assertEquals(HttpStatus.OK.value(), httpServletResponse.getStatus());

	}

	@Test
	public void testLogin() throws Exception {

		Customer customer = new Customer("ash@gmail.com", "ashu", "ashu","added");
		String inputJson = this.mapToJson(customer);

		String URI = "/customer/login";

		Mockito.when(customerService.login(Mockito.any(Customer.class))).thenReturn(Optional.of(customer));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse httpServletResponse = result.getResponse();

		String outputJson = httpServletResponse.getContentAsString();

		assertThat(outputJson).isEqualTo(inputJson);

	}

	@Test
	public void testGetAllCar() throws Exception {
		CarDetails car = new CarDetails("ashu", "ashu@gmail.com", "12345", "honda", "sports", "residental", "14-08-2020",
				"hello", "bokaro", "200");
		String inputJson = this.mapToJson(car);

		String URI = "/customer/booking";

		Mockito.when(carService.saveCarDetails(car)).thenReturn("details saved successfull");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse httpServletResponse = result.getResponse();
		String outputJson = httpServletResponse.getContentAsString();

		assertThat(outputJson).isEqualTo(inputJson);
		assertEquals(HttpStatus.OK.value(), httpServletResponse.getStatus());

	}
	
	@Test
	public void testgetCarDetailsbyId() throws Exception {
		Customer customer = new Customer("ash@gmail.com", "ashu", "ashu", "added");
		ArrayList<CarDetails> car = new ArrayList<>();
		CarDetails car1 = new CarDetails("ashu", "ashu@gmail.com", "12345", "honda", "sports", "residental", "14-08-2020",
				"hello", "bokaro", "200");
		car.add(car1);
		String inputCarJson = this.mapToJson(car);
		String inputCustomerJson = this.mapToJson(customer);
		
		String URI = "/customer/getcardetails";
		
		Mockito.when(carService.getCarDetailsbyemail(Mockito.any(Customer.class))).thenReturn(car);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputCustomerJson).contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse httpServletResponse = result.getResponse();
		String outputJson = httpServletResponse.getContentAsString();

		assertThat(outputJson).isEqualTo(inputCarJson);
		assertEquals(HttpStatus.OK.value(), httpServletResponse.getStatus());
		
	}
}
