package com.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.request.OrderRequest;
import com.order.response.OrderResponse;
import com.order.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
    @Autowired
    private OrderService orderService;

    @GetMapping("/status/check")	
    public String  status() {
    	return "working";
    }
    @PostMapping("/place")
	public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest){
		try {
			System.out.println("request came here");
			OrderResponse resp=orderService.placeOrder(orderRequest);
			return new ResponseEntity<>(resp,HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
    
    @PutMapping("/confirm/{orderId}")
    public ResponseEntity<OrderResponse> confirmOrder(@PathVariable String orderId) {
        try {
            OrderResponse orderResponse = orderService.confirmOrder(orderId);
            return new ResponseEntity<>(orderResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
