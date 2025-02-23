<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>

<%@ page import="com.tap.model.Order , com.tap.model.OrderItem , com.tap.daoimplementation.OrderItemDAOImplementation" %> <!-- Replace with your actual Order class -->
<html>
<head>
    <title>Order List</title>
    <link rel="stylesheet" href="fecthorders.css">
</head>
<body>
    <div class="order-container">
        <h2>My Orders</h2>

        <%
            // Get the session
            List<Order> orders = (List<Order>) session.getAttribute("allOrdersList");
        
       	 	OrderItemDAOImplementation  orderdao = new OrderItemDAOImplementation();

            // Check if the session attribute exists
            if (orders != null && !orders.isEmpty()) {
        %>
            <div class="order-cards">
                <%
                    // Loop through the orders and display them in cards
                    for (Order order : orders) {
                %>
                <div class="order-card">
                    <div class="order-header">
                        <span class="order-id">Order ID: <%= order.getOrderId() %></span>
                        <span class="order-status"><%= order.getStatus() %></span>
                    </div>
                    <div class="order-details">
                        <p><strong>Restaurant:</strong> <%= order.getRestuarantId() %></p>
                        <p><strong>Total Amount:</strong> ₹<%= order.getTotalAmount() %></p>
                        <p><strong>Order Date:</strong> <%= order.getOrderDate() %></p>
                        <p><strong>Payment Mode:</strong> <%= order.getPaymentMode() %></p>

                        <h4>Items:</h4>
                        <ul class="order-items" >
                            <%
                                // Assuming you have a list of item names in the order
                              	List<OrderItem> orderItem = orderdao.getAllOrderItems(order.getOrderId());
                                if (orderItem != null) {
                                   for(OrderItem items : orderItem){
                            %>
                            		<div style="display: flex; align-items: center; justify-content: space-between;" ><li><%= items.getOrderName() %></li>
                            			<li> <%= items.getPrice() %> </li> </div>
                            	<% 		
                            		}
                                 %>
                            
                            <%
                                } else {
                             %>  	
                                	 <li>No items available.</li>
                             <% 	 
                                }
                            %>
                        </ul>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
        <%
            } else {
        %>
            <p class="no-orders">You have no orders.</p>
        <%
            }
        %>
    </div>
</body>
</html>
