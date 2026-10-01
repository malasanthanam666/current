package com.order.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.dto.OrderStatus;
import com.order.entity.OrderEvent;
import com.order.publisher.OrderKafkaPublisher;
import com.order.repository.OrderEventRepository;
import com.order.request.OrderRequest;
import com.order.response.OrderResponse;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class OrderService {
	 private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
	    @Autowired
	    private OrderEventRepository repo;

	    @Autowired
	    private OrderKafkaPublisher publisher;
	
	    public OrderService(OrderEventRepository repo,OrderKafkaPublisher publisher) {
	        this.repo = repo;
	        this.publisher=publisher;
	    }
	
	
	public OrderResponse placeOrder(OrderRequest request) {
		String orderId=UUID.randomUUID().toString();
		//String orderId, String userId, OrderStatus status, String details, LocalDateTime tmstamp
		   OrderEvent orderEvent=new OrderEvent(orderId,request.getUserId(),OrderStatus.CREATED,"Order created successfully", LocalDateTime.now());
	        saveAndPublishEvents(orderEvent);
	        return new OrderResponse(orderEvent.getOrderId(), OrderStatus.CREATED);
	}
	
    private void saveAndPublishEvents(OrderEvent orderEvent){
    	 logger.info("pre  saveAndPublishEvents ::");
    	repo.save(orderEvent);
        publisher.sendOrderEvent(orderEvent);
        logger.info("post saveAndPublishEvents ::");
    }


    public OrderResponse confirmOrder(String orderId) {
        OrderEvent orderEvent=new OrderEvent(orderId,"1",OrderStatus.CONFIRMED,"Order confirmed successfully", LocalDateTime.now());
        saveAndPublishEvents(orderEvent);
        return new OrderResponse(orderId, OrderStatus.CONFIRMED);
    }


}
