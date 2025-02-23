package com.tap.model;

public class OrderItem {

	private int orderItemId;
	private int orderId;
	private String orderName;
	private int menuId;
	private int quantity;
	private double price;
	public OrderItem(int orderItemId, int orderId, String orderName ,int menuId, int quantity, double price) {
		super();
		this.orderName = orderName;
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.price = price;
	}
	public OrderItem(int orderId,  int menuId, String orderName, int quantity, double totalPrice) {
		super();
		
		this.orderId = orderId;
		this.menuId = menuId;
		this.orderName = orderName;
		this.quantity = quantity;
		this.price = totalPrice;
	}
	
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public OrderItem() {
		// TODO Auto-generated constructor stub
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		
		return orderItemId + " " + orderId + " " + menuId + " " + quantity + " " + price;
	}
	
	
}
