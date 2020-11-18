package com.carwash.serviceprovider.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carwash.serviceprovider.model.CarDetails;
import com.carwash.serviceprovider.model.ServiceProvider;
import com.carwash.serviceprovider.service.RabbitMQConsumer;
import com.carwash.serviceprovider.service.ServiceProviderService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/serviceprovider")
public class ServiceProviderController {

	@Autowired
	private ServiceProviderService providerService;

	@Autowired
	RabbitMQConsumer rabbitMQConsumer;
	
	List<CarDetails> details = new ArrayList<>();

	@ApiOperation(value = "check response and request object for Registration", notes = "registration method", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Server error") })
	@PostMapping(value = "/registration")
	private ServiceProvider register(@RequestBody ServiceProvider provider) {

		return providerService.addNewCustomer(provider);

	}

	@ApiOperation(value = "Check request and response object for login", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Server error") })
	@PostMapping(value = "/login")
	private Optional<ServiceProvider> login(@RequestBody ServiceProvider provider) {

		return providerService.login(provider);
	}

	@ApiOperation(value = "get booking cardetails of customer ", response = Iterable.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "ok"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Server error") })
	@GetMapping(value = "/getbookingdetails")
	private List<CarDetails> getbookings() {
		return details;
	}

	@RabbitListener(queues = "carwash.queue")
	@SendTo("detail")
	public List<CarDetails> recievedMessage(CarDetails cardetails) {
		System.out.print("Recieved Message From RabbitMQ: " + cardetails);
		details.add(cardetails);
		return details;

	}
}
