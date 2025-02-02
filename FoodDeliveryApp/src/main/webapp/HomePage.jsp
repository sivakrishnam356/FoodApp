<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@
	page import = "java.util.List ,com.tap.model.Restaurant"
 %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Food Delight</title>
    <style>
body {
	font-family: sans-serif;
	margin: 0;
	padding: 0;
	background-color: rgb(255, 255, 255); /* White background */
}

header {
	background-color: #ffffff;
	color: #FF6347;
	font-weight: 300;
	padding: 15px 0;
	display: flex;
	justify-content: space-between;
	align-items: center;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.logo {
	font-size: 24px;
	font-weight: bold;
}

nav {
	padding: 10px 20px;
}

nav ul {
	list-style: none;
	margin: 0;
	padding: 0;
	display: flex;
	align-items: center;
}

nav ul li {
	margin: 0 15px;
}

nav ul li a {
	color: #333;
	text-decoration: none;
	transition: color 0.3s ease;
}

nav ul li a:hover {
	color: #FF6347;
}

.cart {
	display: flex;
	align-items: center;
}

.search-bar {
	display: flex;
	align-items: center;
}

.search-bar input[type="text"] {
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	width: 250px;
}

.search-bar button {
	background-color: #FF6347;
	color: #fff;
	border: none;
	border-radius: 5px;
	padding: 10px 15px;
	margin-left: 10px;
	cursor: pointer;
}

.container {
	max-width: 960px;
	margin: 0 auto;
}

h2 {
	font-size: 20px;
	margin-bottom: 20px;
}

.restaurant-row {
	display: grid;
	grid-template-columns: repeat(4, 1fr); /* Four columns */
	grid-gap: 20px;
}

.restaurant-card {
	width: 250px; /* Adjust width as needed */
	border-radius: 5px;
	overflow: hidden;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Add a subtle shadow */
	background-color: #fff; /* White background */
	margin-bottom: 20px; /* Add spacing between cards */
}

.restaurant-card img {
	width: 100%;
	height: 150px; /* Set a fixed height */
	object-fit: cover;
}

.card-content {
	padding: 15px;
}

.offer-container {
	position: absolute;
	top: 10px;
	left: 10px;
	background-color: #F97F00; /* Orange background color */
	color: #fff;
	padding: 5px 10px;
	border-radius: 3px;
}

.rating-and-time {
	display: flex;
	align-items: center;
	margin-bottom: 5px;
}

.rating {
	color: #F97F00; /* Orange color for rating */
	margin-right: 5px;
}

.delivery-time {
	color: #777; /* Gray color for delivery time */
}
/* Footer Styles */
footer {
	background-color: rgb(240, 240, 245); /* Light gray background */
	padding: 30px 0;
}

.footer-content {
	max-width: 960px;
	margin: 0 auto;
	display: flex;
	justify-content: space-between;
}

.footer-section {
	margin-right: 40px; /* Adjust spacing between sections */
}

.footer-section h4 {
	margin-bottom: 15px;
}

.footer-section ul {
	list-style: none;
	padding: 0;
}

.footer-section li {
	margin-bottom: 5px;
}

.footer-section a {
	color: black;
	text-decoration: none;
}

.footer-bottom {
	margin-top: 30px;
	text-align: center;
}

.footer-bottom p {
	margin-bottom: 10px;
}

.social-links {
	display: flex;
	justify-content: center;
}

.social-links a {
	margin-right: 10px;
	color: #fff;
}
</style>
</head>
<body>

    <!-- Header Section -->
    <header>
        <div class="logo">Food Delight</div>
        <nav>
            <ul>
                <li><a href="HomePage.jsp">Home</a></li>
                <li><a href="OrdersServlet">MyOrders</a></li>
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

     <div class="container">
    <h2>Restaurants with online food delivery</h2>

    <div class="restaurant-row">
        <%
            session = request.getSession();
            List<Restaurant> allrestaurants = (List<Restaurant>) session.getAttribute("restuarants");

            if (allrestaurants != null && !allrestaurants.isEmpty()) {
                for (Restaurant restaurant : allrestaurants) {
        %>
            <div class="restaurant-card">
            	<a href="MenuServlet?restaurantId=<%= restaurant.getRestaurantId() %>" style="text-decoration: none; color: #000;"> 
                <img src="<%= restaurant.getImagePath() %>" alt="<%= restaurant.getName() %>"> 
                <div class="card-content">
                    <h3><%= restaurant.getName() %></h3>
                    <div class="rating-and-time">
                        <span class="rating">★ <%= restaurant.getRating() %></span>
                        <span class="delivery-time">• <%= restaurant.getEstimatedTime() %></span> 
                    </div>
                    <p><%= restaurant.getCuisineType() %></p>
                    <p><%= restaurant.getAddress() %></p>
                </div>
                </a>
            </div>
        <%
                }
            }
        %>
    </div>
</div>
    <footer>
        <div class="container">
            <div class="footer-content">
                <div class="footer-section">
                    <h4>Company</h4>
                    <ul>
                        <li><a href="#">About Us</a></li>
                        <li><a href="#">Swiggy Corporate</a></li>
                        <li><a href="#">Careers</a></li>
                        <li><a href="#">Team</a></li>
                        <li><a href="#">Swiggy One</a></li>
                        <li><a href="#">Swiggy Instamart</a></li>
                        <li><a href="#">Swiggy Dineout</a></li>
                        <li><a href="#">Swiggy Genie</a></li>
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
                    <h4>Explore with Swiggy</h4>
                    <ul>
                        <li><a href="#">Life at Swiggy</a></li>
                        <li><a href="#">Swiggy News</a></li>
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
        </div>
    </footer>
    

</body>
</html>