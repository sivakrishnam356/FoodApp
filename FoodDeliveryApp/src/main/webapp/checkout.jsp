<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.tap.daoimplementation.Cart, com.tap.model.CartItem, com.tap.model.Restaurant" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="checkout.css">
</head>

<body>

    <div class="container mt-4">
        <!-- Checkout Header -->
        <div class="d-flex justify-content-between align-items-center mb-3">
            <a href="menu.jsp" class="btn btn-outline-dark">Back</a>
            <h2 class="fw-bold text-danger">Checkout</h2>
        </div>

        <%
            session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            Restaurant restaurant = (Restaurant) session.getAttribute("restaurant");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = sdf.format(new Date());

            double subTotal = 0;
        %>

        <!-- Order Summary -->
        <div class="card p-3 shadow-sm mb-3">
            <h4 class="fw-bold">Your Order</h4>
            <hr>
            <% if (cart != null && !cart.cart.isEmpty()) {
                for (CartItem cartItem : cart.cart.values()) {
                    double itemTotal = cartItem.getPrice() * cartItem.getQuantity();
                    subTotal += itemTotal;
            %>
            <div class="d-flex justify-content-between py-2">
                <span><strong><%= cartItem.getName() %></strong> × <%= cartItem.getQuantity() %></span>
                <span>$<%= itemTotal %></span>
            </div>
            <% } } %>
            <hr>
            <div class="d-flex justify-content-between">
                <span>Subtotal:</span>
                <strong>$<%= subTotal %></strong>
            </div>
            <div class="d-flex justify-content-between">
                <span>Delivery Fee:</span>
                <strong>$50</strong>
            </div>
            <hr>
            <div class="d-flex justify-content-between fs-5 fw-bold text-danger">
                <span>Total:</span>
                <span>$<%= subTotal + 50 %></span>
            </div>
        </div>

        <!-- Delivery Address -->
        <div class="card p-3 shadow-sm mb-3">
            <h4 class="fw-bold">Delivery Address</h4>
            <p class="mb-1">BTM LAYOUT <a href="#" class="text-danger">Edit</a></p>
            <hr>
            <p><strong>Estimated Delivery:</strong> <%= restaurant.getEstimatedTime() %></p>
        </div>

        <!-- Payment Methods -->
        <div class="card p-3 shadow-sm mb-3">
            <h4 class="fw-bold">Payment</h4>
            <form action="OrderServlet" method="POST">
                <!-- Hidden Inputs -->
                <input type="hidden" name="userEmail" value="<%= (String) session.getAttribute("userEmail") %>">
                <input type="hidden" name="restaurantId" value="<%= restaurant.getRestaurantId() %>">
                <input type="hidden" name="orderDate" value="<%= formattedDate %>">
                <input type="hidden" name="totalAmount" value="<%= subTotal + 50 %>">
                <input type="hidden" name="status" value="Delivered">

                <div class="form-check">
                    <input class="form-check-input" type="radio" name="payment-method" value="phonepe" checked>
                    <label class="form-check-label">PhonePe</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="payment-method" value="card">
                    <label class="form-check-label">Credit/Debit Card</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="payment-method" value="cash">
                    <label class="form-check-label">Cash on Delivery</label>
                </div>

                <button class="btn btn-danger btn-lg w-100 mt-3">Place Order • $<%= subTotal + 50 %></button>
            </form>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
