package com.carwash.serviceprovider.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.carwash.serviceprovider.model.CarDetails;
import com.carwash.serviceprovider.repository.ServiceProviderRepo;

@Component
public class RabbitMQConsumer {
	

}
