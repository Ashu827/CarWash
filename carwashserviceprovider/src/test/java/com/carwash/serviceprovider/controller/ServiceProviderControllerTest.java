package com.carwash.serviceprovider.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
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

import com.carwash.serviceprovider.model.CarDetails;
import com.carwash.serviceprovider.model.ServiceProvider;
import com.carwash.serviceprovider.service.RabbitMQConsumer;
import com.carwash.serviceprovider.service.ServiceProviderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value=ServiceProviderController.class)
public class ServiceProviderControllerTest {

	@MockBean
	private ServiceProviderService providerService;

	@MockBean
	RabbitMQConsumer rabbitMQConsumer;
	
	@Autowired
	private MockMvc mockMvc;
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

	@Test
	public void testRegister() throws Exception {
		ServiceProvider provider = new ServiceProvider("ashu@gmail.com", "ashu", "ashu");
		String inputJson = this.mapToJson(provider);

		String URI = "/serviceprovider/registration";
		
		Mockito.when(providerService.addNewCustomer(Mockito.any(ServiceProvider.class))).thenReturn(provider);
		
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
		ServiceProvider provider = new ServiceProvider("ash@gmail.com", "ashu", "ashu");
		String inputJson = this.mapToJson(provider);

		String URI = "/serviceprovider/login";

		Mockito.when(providerService.login(Mockito.any(ServiceProvider.class))).thenReturn(Optional.of(provider));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse httpServletResponse = result.getResponse();

		String outputJson = httpServletResponse.getContentAsString();

		assertThat(outputJson).isEqualTo(inputJson);
		System.out.print(httpServletResponse.getStatus() + "httpServletResponse.getStatus()");
		System.out.print(HttpStatus.OK.value() + "HttpStatus.OK.value()");
		 assertEquals(HttpStatus.OK.value(), httpServletResponse.getStatus());

	}
	
	/*
	 * @Test public void testGetbookings() throws Exception { List<CarDetails>
	 * details = new ArrayList<>(); CarDetails car1 = new CarDetails("ashu",
	 * "ashu@gmail.com", "12345", "honda", "sports", "residental", "14-08-2020",
	 * "hello", "bokaro", "200"); CarDetails car2 = new CarDetails("ashu",
	 * "ashu@gmail.com", "12345", "honda", "sports", "residental", "14-08-2020",
	 * "hello", "bokaro", "200"); details.add(car1); details.add(car2);
	 * 
	 * String inputJson = this.mapToJson(details);
	 * 
	 * String URI = "/serviceprovider/getbookingdetails";
	 * 
	 * Mockito.when(recievedMessage(Mockito.any(CarDetails.class))).thenReturn(
	 * details);
	 * 
	 * RequestBuilder requestBuilder =
	 * MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
	 * .content(inputJson).contentType(MediaType.APPLICATION_JSON);
	 * 
	 * MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	 * MockHttpServletResponse httpServletResponse = result.getResponse();
	 * 
	 * String outputJson = httpServletResponse.getContentAsString();
	 * 
	 * assertThat(outputJson).isEqualTo(inputJson);
	 * System.out.print(httpServletResponse.getStatus() +
	 * "httpServletResponse.getStatus()"); System.out.print(HttpStatus.OK.value() +
	 * "HttpStatus.OK.value()"); assertEquals(HttpStatus.OK.value(),
	 * httpServletResponse.getStatus()); }
	 */
}
