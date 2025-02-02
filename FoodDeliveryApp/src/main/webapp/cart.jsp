<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tap.daoimplementation.Cart, com.tap.model.CartItem , com.tap.model.Restaurant , com.tap.model.Menu"  %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
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
                <img src="<%= restaurant.getImagePath() %>" alt="Restaurant Logo" class="restaurant-logo">
            <% } else { %>
                <img src="placeholder-restaurant-logo.png" alt="Restaurant Logo" class="restaurant-logo">
            <% } %>
            <div class="restaurant-info">
                <h2><%= (restaurant != null) ? restaurant.getName() : "Restaurant" %></h2>
                <p><%= (restaurant != null) ? restaurant.getAddress() : "" %></p>
            </div>
        </div>

        <div class="cart-items">
            <% if (cart != null && !cart.cart.isEmpty()) { 
                for (CartItem cartItem : cart.cart.values()) { 
            %>
            <div class="cart-item">
                <img src="<%= cartItem.getImage() %>" alt="<%= cartItem.getName() %>">

                <div class="cart-item-details">
                    <p class="item-name"><%= cartItem.getName() %></p>

                    <div class="item-actions">
                        <div class="item-quantity">
                            <form action="CartServlet" method="GET">
                                <input type="hidden" name="menuId" value="<%= cartItem.getId() %>">
                                <input type="hidden" name="quantity" value="<%= cartItem.getQuantity() - 1 %>">
                                <input type="hidden" name="action" value="update">
                                <button class="quantity-btn" <% if(cartItem.getQuantity() == 1) { %> disabled <% } %> >-</button>
                            </form>
                            
                            <span class="quantity-value"><%= cartItem.getQuantity() %></span>

                            <form action="CartServlet" method="GET">
                                <input type="hidden" name="menuId" value="<%= cartItem.getId() %>">
                                <input type="hidden" name="quantity" value="<%= cartItem.getQuantity() + 1 %>">
                                <input type="hidden" name="action" value="update">
                                <button class="quantity-btn">+</button>
                            </form>
                        </div>
                        <p class="item-price">₹<%= cartItem.getPrice() %></p>
                    </div>
                </div>
            </div>
            <% } 
            } else { %>
            <p class="empty-cart">Your cart is empty.</p>
            <% } %>
        </div>

        <div class="bill-details">
            <h3>Bill Details</h3>
            <p>TO PAY <span>₹ <%= session.getAttribute("totalPrice") %></span></p>
        </div>

        <!-- Buttons for Add More Items and Proceed to Checkout -->
        <div class="button-container">
            <button class="add-more-items" onclick="window.location.href='Menu.jsp'">Add More Items</button>
            <button class="proceed-to-checkout" onclick="window.location.href='checkout.jsp'">Proceed to Checkout</button>
        </div>
    </div>

</body>
</html>
