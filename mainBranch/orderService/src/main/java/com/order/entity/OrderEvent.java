package com.order.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.order.dto.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//enum OrderStatus{"CONFIRMED","CANCELED","SHIPPED"};


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "OrderEvent")
public class OrderEvent {

	@Id
	private String orderId;
	private String userId;
	private OrderStatus status;
	private String details;
	private LocalDateTime tmstamp;
	public OrderEvent(String orderId, String userId, OrderStatus status, String details, LocalDateTime tmstamp) {
		
		this.orderId = orderId;
		this.userId = userId;
		this.status = status;
		this.details = details;
		this.tmstamp = tmstamp;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public LocalDateTime getTmstamp() {
		return tmstamp;
	}
	public void setTmstamp(LocalDateTime tmstamp) {
		this.tmstamp = tmstamp;
	}
	
	
	
}
