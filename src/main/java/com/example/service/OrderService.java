package com.example.service;

import java.util.List;

import com.example.entity.Order;

public interface OrderService {

	Order createOrder(Order oredr);

	List<Order> getAllOrders();

	Order getOrderById(Long Id);

	Order updateOrder(Long Id, Order order);

	void deleteOrder(Long Id);
}

