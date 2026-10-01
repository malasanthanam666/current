package com.order.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.order.entity.OrderEvent;

import tools.jackson.databind.ObjectMapper;

@Component
public class OrderKafkaPublisher {
	 private static final Logger logger = LoggerFactory.getLogger(OrderKafkaPublisher.class);
	@Autowired
	public KafkaTemplate<String,String> kafkaTemplate;
	public OrderKafkaPublisher(KafkaTemplate<String,String> kafkaTemplate) {
		this.kafkaTemplate=kafkaTemplate;
	}
	
	@Value("${order.event.topicName}")
	private String topicName;
	 
	
	public void sendOrderEvent(OrderEvent orderEvent) {
	    logger.info(topicName+"--topic name---	kafkaTemplate.orderEventsend:"+orderEvent.getOrderId());
	    ObjectMapper mapper = new ObjectMapper();
	    String jsonString = mapper.writeValueAsString(orderEvent);
		kafkaTemplate.send(topicName,jsonString);
	    logger.info("Microservice task finished successfully.");
	}
}
