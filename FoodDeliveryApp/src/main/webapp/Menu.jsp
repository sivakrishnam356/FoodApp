<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@
	page import = "java.util.List ,com.tap.model.Menu"
 %>
 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Menu </title>
    <link rel="stylesheet" href="Menu.css">
</head>
<body>

	<h1> Menu </h1>

    <div class="menu-container">
    
    	<%
    		 session = request.getSession();
    	
    		 List<Menu> menus = (List<Menu>) session.getAttribute("allMenus");
    		 
    		 if(menus != null && !menus.isEmpty()){
    			 for(Menu menu : menus){
    				 
    			 	
    		 
    	
    	%>
        <div class="item-card">
            <img src="<%= menu.getImagePath() %>" alt="<%= menu.getItemName() %>">
            <div class="card-content">
                <h3><%= menu.getItemName() %></h3>
                <p>₹ <%= menu.getPrice() %></p>
                <div class="rating">★ <%= menu.getRatings() %> (147)</div>
                <p>Serves 1 | <%= menu.getDescription() %></p>
               
                <form action="CartServlet">
                
                	<input type = "hidden" name = "restaurantId" value = "<%= menu.getRestaurantId() %>">
                	<input type = "hidden" name = "menuId" value = "<%= menu.getMenuId() %>">
                	<input type = "number" name = "quantity" value = "1" min = "1">
                	<input type = "hidden" name = "action" value = "add">
                	
                	 <button class="add-button">ADD</button>
                	
                
                </form>
            </div>
        </div>
        
        <%
    			 }
    		 }
        %>

        
    </div>

</body>
</html>