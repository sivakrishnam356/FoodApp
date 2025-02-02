<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.tap.model.Menu" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu</title>
    <link rel="stylesheet" href="menu.css">
</head>

<body>
    <!-- Header Section -->
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

    <!-- Menu Container -->
    <div class="menu-container">
        <%
            session = request.getSession();
            List<Menu> menus = (List<Menu>) session.getAttribute("allMenus");
            if (menus != null && !menus.isEmpty()) {
                for (Menu menu : menus) {
        %>
                    <!-- Menu Item Card -->
                    <div class="item-card">
                        <img src="<%= menu.getImagePath() %>" alt="<%= menu.getItemName() %>">
                        <div class="card-content">
                            <h3><%= menu.getItemName() %></h3>
                            <p>₹ <%= menu.getPrice() %></p>
                            <div class="rating">★ <%= menu.getRatings() %> (147)</div>
                            <p>Serves 1 | <%= menu.getDescription() %></p>

						<!-- Add to Cart Form -->
						<form action="CartServlet" method="GET" class="cart-form">
							<input type="hidden" name="restaurantId"
								value="<%=menu.getRestaurantId()%>"> <input
								type="hidden" name="menuId" value="<%=menu.getMenuId()%>">
							<div class="quantity-container">
								<label for="quantity" class="quantity-label"></label> <input
									type="hidden" name="quantity" value="1" min="1"
									class="quantity-input">
							</div>
							<input type="hidden" name="action" value="add">
							<button onclick="countCart(  <%= menu.getPrice() %>)" class="add-to-cart-btn">Add to Cart</button>
						</form>
			</div>
                    </div>
        <%
                }
            }
        %>
    </div>
     <footer>
        <div class="container">
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
        </div>
    </footer>


	<script>
		let cartItems = []; // Array to store unique items
		let cartCount = 0; // Cart count

		function addItemToCart(itemName) {
			// Check if the item already exists in the cart
			if (!cartItems.includes(itemName)) {
				cartItems.push(itemName); // Add new item to cart
				cartCount++; // Increase the cart count
			}
			updateCartCount(); // Update the cart count display
		}

		function updateCartCount() {
			// Update the cart count displayed in the header
			document.getElementById('cartCount').textContent = cartCount;
		}
	</script>



</body>

</html>
