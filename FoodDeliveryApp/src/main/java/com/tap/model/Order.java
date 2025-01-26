package com.tap.model;

import java.util.Date;

public class Order {

	private int orderId;
	private int userId ;
	private int restuarantId;
	private Date orderDate;
	private double totalAmount;
	private String status;
	private String paymentMode;
	public Order(int orderId, int userId, int restuarantId, Date orderDate, double totalAmount, String status,
			String paymentMode) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.restuarantId = restuarantId;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentMode = paymentMode;
	}
	
	
	public Order() {
		// TODO Auto-generated constructor stub
	}


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getRestuarantId() {
		return restuarantId;
	}


	public void setRestuarantId(int restuarantId) {
		this.restuarantId = restuarantId;
	}


	public Date getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}


	public double getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getPaymentMode() {
		return paymentMode;
	}


	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	
}
