<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Success</title>
    <link rel="stylesheet" href="success.css">
</head>

<body>
    <!-- Header Section -->
    <header>
        <div class="logo">Food Delight</div>
        <nav>
            <ul>
                <li><a href="HomePage.jsp">Home</a></li>
                <li><a href="MenuServlet">Menu</a></li>
                <li><a href="about.jsp">About</a></li>
                <li><a href="contact.jsp">Contact</a></li>
                <li><a href="profile.jsp">Profile</a></li>
                <div class="cart">
                    <li><a href="cart.jsp">Cart <span id="cartCount">0</span></a></li>
                </div>
            </ul>
        </nav>
        <div class="search-bar">
            <input type="text" placeholder="Search">
            <button>Search</button>
        </div>
    </header>

    <!-- Main Content -->
    <div class="container">
        <h1>Order Successful!</h1>
        <p>Thank you for your order. Your order has been successfully placed and is being processed.</p>
        <p>Order ID: <%= request.getAttribute("orderId") %></p>
        <p>We will notify you once your order is out for delivery.</p>
        <a href="HomePageServlet" class="btn">Continue Ordering</a>
    </div>

    <!-- Footer Section -->
    <footer>
        <div class="footer-content">
            <div class="footer-section">
                <h4>Company</h4>
                <ul>
                    <li><a href="#">About Us</a></li>
                    <li><a href="#">Food Delight Corporate</a></li>
                    <li><a href="#">Careers</a></li>
                    <li><a href="#">Team</a></li>
                    <li><a href="#">Food Delight One</a></li>
                    <li><a href="#">Food Delight Instamart</a></li>
                    <li><a href="#">Food Delight Dineout</a></li>
                    <li><a href="#">Food Delight Genie</a></li>
                </ul>
            </div>
            <div class="footer-section">
                <h4>Help & Support</h4>
                <ul>
                    <li><a href="#">Contact us</a></li>
                    <li><a href="#">Help & Support</a></li>
                    <li><a href="#">Partner with us</a></li>
                    <li><a href="#">Ride with us</a></li>
                </ul>
            </div>
            <div class="footer-section">
                <h4>Legal</h4>
                <ul>
                    <li><a href="#">Terms & Conditions</a></li>
                    <li><a href="#">Cookie Policy</a></li>
                    <li><a href="#">Privacy Policy</a></li>
                    <li><a href="#">Investor Relations</a></li>
                </ul>
            </div>
            <div class="footer-section">
                <h4>Explore with Food Delight</h4>
                <ul>
                    <li><a href="#">Life at Food Delight</a></li>
                    <li><a href="#">Food Delight News</a></li>
                    <li><a href="#">Snackables</a></li>
                </ul>
            </div>
        </div>
        <div class="footer-bottom">
            <div class="social-links">
                <a href="#"><i class="fab fa-facebook-f"></i></a>
                <a href="#"><i class="fab fa-twitter"></i></a>
                <a href="#"><i class="fab fa-instagram"></i></a>
            </div>
        </div>
    </footer>
</body>
</html>
