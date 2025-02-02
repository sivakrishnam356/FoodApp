<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import = "com.tap.daoimplementation.Cart, com.tap.model.CartItem , com.tap.model.Restaurant , com.tap.daoimplementation.UserDAOImplementation" %>

<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Modern Checkout</title>
<link rel="stylesheet" href="checkout.css">
</head>
<body>
	<div class="container">
		<!-- Header -->
		<div class="header">
			<button class="back-btn">
				<i class="fas fa-arrow-left"></i>
			</button>
			<h1>Checkout</h1>
			<div class="progress-steps">
				<span class="step active">1</span> <span class="step">2</span> <span
					class="step">3</span>
			</div>
		</div>

		<%
     
      session = request.getSession();
     
      Cart cart = (Cart) session.getAttribute("cart");
      
      Restaurant restaurant = (Restaurant) session.getAttribute("restaurant");
      
      
      
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      String formattedDate = sdf.format(new Date());
      
      String name = null;
      double price = 0;
      int quantity = 0;
      double subTotal = 0;
    %>

		<!-- Order Summary -->
		<div class="card">
			<h2 class="card-title">
				<i class="fas fa-shopping-bag"></i> Your Order
			</h2>
			<%
        if (cart != null && !cart.cart.isEmpty()) {
          for (CartItem cartItem : cart.cart.values()) {
            name =  cartItem.getName();
            price = cartItem.getPrice();
             quantity = cartItem.getQuantity();
            subTotal += price * quantity;
      %>
			<div class="order-item">
				<span><%= cartItem.getName() %> x<%= cartItem.getQuantity() %></span>
				<span>$<%= cartItem.getPrice() * cartItem.getQuantity() %></span>
			</div>
			<%
          }
        }
        double total = subTotal + 50;
      %>
			<div class="price-breakup">
				<div class="price-row">
					<span>SubTotal</span> <span>$<%= subTotal %></span>
				</div>
				<div class="price-row">
					<span>Delivery</span> <span>$ 50 </span>
				</div>
				<div class="price-row total">
					<span>Total</span> <span>$<%= total %></span>
				</div>
			</div>
		</div>

		<!-- Delivery Address -->
		<div class="card">
			<h2 class="card-title">
				<i class="fas fa-map-marker-alt"></i> Delivery
			</h2>
			<div class="address">
				<p>BTM LAYOUT</p>
				<button class="edit-btn">Edit</button>
			</div>
			<div class="delivery-time">
				<i class="fas fa-clock"></i> Estimated delivery:
				<%= restaurant.getEstimatedTime() %>
			</div>
		</div>

		

		<!-- Promo Code -->
		<div class="card promo-card">
			<input type="text" placeholder="Enter promo code">
			<button class="apply-btn">Apply</button>
		</div>

		<!-- Place Order Button -->
		<form action="OrderServlet" method="POST">
			<!-- Other hidden inputs -->
			<input type="hidden" name="userEmail"
				value="<%= (String) session.getAttribute("userEmail") %>"> <input
				type="hidden" name="restaurantId" value="<%= restaurant.getRestaurantId() %>">
			<input type="hidden" name="orderDate"
				value="<%= formattedDate %>"> <input type="hidden"
				name="totalAmount" value="<%= total %>"> <input
				type="hidden" name="status" value="Delivered">
				name="totalAmount" value="<%= total %>"> 

			<!-- Payment Methods -->
		<div class="card">
			<h2 class="card-title">
				<i class="fas fa-credit-card"></i> Payment
			</h2>

			<!-- Payment Methods -->
			<div class="payment-methods">
					<label class="payment-method"> <input type="radio"
						name="payment-method" value="phonepe" checked> <i
						class="fab fa-cc-visa"></i> <span>PhonePe</span>
					</label> <label class="payment-method"> <input
						type="radio" name="payment-method" value="card"> <i
						class="fab fa-paypal"></i> <span>Card</span>
					</label> <label class="payment-method"> <input
						type="radio" name="payment-method" value="cash"> <i
						class="fas fa-money-bill-wave"></i> <span>Cash on Delivery</span>
					</label>
				</div>
		</div>

			<!-- Place Order Button -->
			<button class="place-order-btn">
				Place Order • $<%= total %>
				<i class="fas fa-arrow-right"></i>
			</button>
		</form>
</body>
</html>
