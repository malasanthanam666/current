package com.user.shipproject.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


import com.user.shipproject.dto.OrderStatus;
import com.user.shipproject.entity.OrderEvent;
import com.user.shipproject.repository.OrderEventRepository;

@Service
public class ShippingEventService {
	 private static final Logger logger = LoggerFactory.getLogger(ShippingEventService.class);
	@Autowired
    private OrderEventRepository repository;
	
	@KafkaListener(topics="order-events",groupId="shipping-service")
	public void consumeOrderEvent(OrderEvent orderEvent) {
		if(orderEvent.getStatus().equals(OrderStatus.CONFIRMED)) {
			shipOrder(orderEvent.getOrderId());
		}
		
	}
//String orderId, String userId, OrderStatus status, String details, LocalDateTime tmstamp
	 // Ship the order
    public void shipOrder(String orderId) {
        OrderEvent orderEvent = new OrderEvent(orderId, "1",OrderStatus.SHIPPED, "Order Shipped successfully", LocalDateTime.now());
        repository.save(orderEvent);
        logger.info("shipOrder saved::"+orderEvent.toString());
    }

    // Deliver the order
    public void deliverOrder(String orderId) {
        OrderEvent orderEvent = new OrderEvent(orderId,"1", OrderStatus.DELIVERED, "Order delivered successfully", LocalDateTime.now());
        repository.save(orderEvent);
        logger.info("deliverOrder saved::"+orderEvent.toString());
    }
}
