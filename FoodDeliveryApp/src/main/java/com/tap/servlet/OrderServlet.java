package com.tap.servlet;

import com.tap.daoimplementation.Cart;
import com.tap.daoimplementation.OrderDAOImplementation;
import com.tap.daoimplementation.OrderItemDAOImplementation;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tap.model.CartItem;
import com.tap.model.Order;
import com.tap.model.OrderItem;
import com.tap.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class OrderServlet extends HttpServlet {
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		// getting Order information from checkout.jsp ;
		String userEmail = req.getParameter("userEmail");
		
		int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
		
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 Date orderDate =  null;
		try {
			orderDate =  dateFormat.parse(req.getParameter("orderDate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 orderDate = new java.util.Date();
		java.sql.Date sqlOrderDate = new java.sql.Date(orderDate.getTime());
		
		double totalAmount = Double.parseDouble(req.getParameter("totalAmount"));
		
		String status = req.getParameter("status");
		
		String paymentType = req.getParameter("payment-method");
		
		
		
		// creating order object 
		Order orders = new Order(userEmail,restaurantId,sqlOrderDate,totalAmount,status,paymentType);
		
		// adding to the order table 
		OrderDAOImplementation orderdao = new OrderDAOImplementation();
	
		int orderId = orderdao.addOrder(orders);
		
		  HttpSession session = req.getSession();
		  
		 
		
		
		// Get the Cart from the session
      
        Cart cart = (Cart) session.getAttribute("cart");
        Restaurant restaurant = (Restaurant) session.getAttribute("restaurant");
        
        
        // creating OrderItem object
        
        for (CartItem cartItem : cart.cart.values()) {
        	
        	
			
            OrderItem orderItem = new OrderItem(orderId,cartItem.getId(),cartItem.getName()
            		,cartItem.getQuantity(),cartItem.getPrice());
            
            OrderItemDAOImplementation orderItemdao = new OrderItemDAOImplementation();
            
            orderItemdao.addOrderItem(orderItem);
		}
		
		// redirecting to success page
        
        req.setAttribute("orderId", orderId);
		RequestDispatcher rd = req.getRequestDispatcher("sucess.jsp");
		rd.forward(req, resp);
		
		
	}

}
