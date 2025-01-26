<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tap.daoimplementation.Cart, com.tap.model.CartItem , com.tap.model.Restaurant" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title> Cart </title>
    <link rel="stylesheet" href="cart.css">
</head>
<body>

    <%  
        Cart cart = (Cart) session.getAttribute("cart");
        Restaurant restaurant = (Restaurant) session.getAttribute("restaurant");
    %>

    <div class="cart-container">
        <div class="cart-header">
            <% if (restaurant != null) { %>
                <img src="<%= restaurant.getImagePath() %>" alt="KFC Logo" class="restaurant-logo">
            <% } else { %>
                <img src="placeholder-restaurant-logo.png" alt="Restaurant Logo" class="restaurant-logo">
            <% } %>
            <div class="restaurant-info">
                <h2><%= (restaurant != null) ? restaurant.getName() : "Restaurant" %></h2>
                <p><%= (restaurant != null) ? restaurant.getAddress() : "" %></p>
            </div>
        </div>

        <div class="cart-items">
            <%
                if (cart != null && !cart.cart.isEmpty()) { // Check if cart is not empty
                    for (CartItem cartItem : cart.cart.values()) {
            %>
                <div class="cart-item">
                    <p><%= cartItem.getName() %></p>
                    <div class="item-quantity">
                        <button>-</button>
                        <span class="quantity-value"><%= cartItem.getQuantity() %></span>
                        <button>+</button>
                    </div>
                    <p>₹<%= cartItem.getPrice() %></p>
                </div>
            <%
                    }
                } else {
            %>
                <p class="empty-cart">Your cart is empty.</p>
            <%
                }
            %>
        </div>

        <div class="bill-details">
            <h3>Bill Details</h3>
            <p>Item Total <span>₹1087</span></p>
            <p>Delivery Fee | 8.1 kms <span>₹ 91 </span></p>
            <p>Delivery Tip <span class="add-tip">Add tip</span></p>
            <p>Platform Fee <span>₹10</span></p>
            <p>GST and Restaurant Charges <span>₹95.15</span></p>
        </div>

        <div class="total-pay">
            <h3>TO PAY <span>₹1283</span></h3>
        </div>
    </div>

</body>
</html>