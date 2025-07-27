package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.OrderRequest;
import com.example.dto.OrderResponse;
import com.example.entity.Order;
import com.example.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	@Autowired
	OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping("/create")
	public ResponseEntity<Order> createOrder(@RequestBody @Valid OrderRequest request) {
		Order order = Order.builder().productId(request.getProductId()).userId(request.getUserId())
				.quantity(request.getQuantity()).status(request.getStatus()).build();
		Order createdOrder = orderService.createOrder(order);
		return new ResponseEntity<Order>(createdOrder, HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<List<OrderResponse>> getAllOrders() {
		List<Order> allOrders = orderService.getAllOrders();
		List<OrderResponse> listOfOrders = allOrders.stream().map(this::toOrderResponse).toList();
		return new ResponseEntity<List<OrderResponse>>(listOfOrders, HttpStatus.OK);
	}

	@GetMapping("/{Id}")
	public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long Id) {
		Order orderById = orderService.getOrderById(Id);
		OrderResponse order = new OrderResponse();
		order.setId(orderById.getId());
		order.setProductId(orderById.getProductId());
		order.setQuantity(orderById.getQuantity());
		order.setCreatedAt(orderById.getCreatedAt());
		order.setStatus(orderById.getStatus());
		order.setUserId(orderById.getUserId());
		return new ResponseEntity<OrderResponse>(order, HttpStatus.OK);
	}

	@PutMapping("/{Id}")
	public ResponseEntity<Order> updateOrder(@PathVariable Long Id, @RequestBody OrderRequest request) {
		Order order = Order.builder().productId(request.getProductId()).quantity(request.getQuantity())
				.status(request.getStatus()).userId(request.getUserId()).build();
		Order updateOrder = orderService.updateOrder(Id, order);
		return new ResponseEntity<Order>(updateOrder, HttpStatus.OK);

	}

	@DeleteMapping("/{Id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable Long Id) {
		orderService.deleteOrder(Id);
		return ResponseEntity.noContent().build();
	}

	private OrderResponse toOrderResponse(Order order) {
		return OrderResponse.builder().id(order.getId()).productId(order.getProductId()).userId(order.getUserId())
				.quantity(order.getQuantity()).status(order.getStatus()).createdAt(order.getCreatedAt())
				.updatedAt(order.getUpdatedAt()).build();
	}
	
	@GetMapping("/test")
	public ResponseEntity<String> testSwagger() {
	    return ResponseEntity.ok("Swagger works!");
	}

}
