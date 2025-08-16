package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Order;
import com.example.exception.ResourceNotFoundException;
import com.example.reporitory.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orderRepository;

	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public Order createOrder(Order oredr) {
		Order createdOrder = orderRepository.save(oredr);
		return createdOrder;
	}

	@Override
	public List<Order> getAllOrders() {
		List<Order> findAll = orderRepository.findAll();
		return findAll;
	}

	@Override
	public Order getOrderById(Long Id) {
		Order orderByID = orderRepository.findById(Id)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found with ID " + Id));
		return orderByID;
	}

	@Override
	public Order updateOrder(Long Id, Order order) {
		Order orderById = getOrderById(Id);
		orderById.setProductId(order.getProductId());
		orderById.setUserId(order.getUserId());
		orderById.setQuantity(order.getQuantity());
		orderById.setStatus(order.getStatus());
		return orderRepository.save(orderById);
	}

	@Override
	public void deleteOrder(Long Id) {
		orderRepository.deleteById(Id);

	}

}

