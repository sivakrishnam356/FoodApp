package com.tap.servlet;

import java.io.IOException;
import java.util.List;

import com.tap.daoimplementation.OrderDAOImplementation;
import com.tap.daoimplementation.OrderItemDAOImplementation;
import com.tap.model.Order;
import com.tap.model.OrderItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/FetchOrderServlet")
public class FetchOrderServlet extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		OrderDAOImplementation orderdao = new OrderDAOImplementation();
		
		List<Order> allOrders = orderdao.getAllOrders();
		
		
		
		
		
		HttpSession session = req.getSession();
		
		session.setAttribute("allOrdersList", allOrders);
		
		
		
		resp.sendRedirect("fetchorders.jsp");
		
		
		 
		 
	}
}
