package com.tap.dao;

import java.util.List;

import com.tap.model.Order;
import com.tap.model.User;

public interface OrderDAO {

	int addOrder(Order order);
	Order getOrder(int orderId);
	void updateOrder(Order order,int orderId);
	void deleteOrder(int orderId);
	List<Order> getAllOrders();
}
