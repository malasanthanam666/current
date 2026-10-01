package com.order.response;

import com.order.dto.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private String orderId;
    private OrderStatus status;
	public OrderResponse(String orderId, OrderStatus status) {
		super();
		this.orderId = orderId;
		this.status = status;
	}
    
}