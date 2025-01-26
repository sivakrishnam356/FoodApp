package com.tap.servlet;

import java.io.IOException;

import com.tap.dao.MenuDAO;
import com.tap.daoimplementation.Cart;
import com.tap.daoimplementation.MenuDAOImplementation;
import com.tap.model.CartItem;
import com.tap.model.Menu;
import com.tap.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.tap.daoimplementation.RestaurantDAOImplementation;

public class CartServlet extends HttpServlet {
	
	private HttpSession session;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			// get the cart from the session
			session = req.getSession();
			
			Cart cart = (Cart)session.getAttribute("cart");
			
			// Get the restaurantId and currentRestaurant from id's;
			
			int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
			
			Integer currentRestaurantIdObj = (Integer) session.getAttribute("restaurantId");
			
			int currentRestaurantId = (currentRestaurantIdObj != null) ? currentRestaurantIdObj : -1;
			
			System.out.println("hii");
			
			// check if the cart is null or not 
			
			if(cart == null || restaurantId != currentRestaurantId) {
				
				 cart = new Cart();
				 
				 session.setAttribute("cart", cart);
				 session.setAttribute("restaurantId", restaurantId);
			}
			
			// get the action from the request
			
			String action = req.getParameter("action");
			
			if(action.equals("add")) {
				addToCart(req,cart);
			}else if(action.equals("update")) {
				updateCart(req,cart);
			}else {
				removeCart(req,cart);
			}
			
			RestaurantDAOImplementation restaurantdao = new RestaurantDAOImplementation();
			
			Restaurant restaurant = restaurantdao.getRestaurant(restaurantId);
			
			if(restaurant != null) {
				session.setAttribute("restaurant", restaurant);
			}
			
			resp.sendRedirect("cart.jsp");
			
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
			
		
		
	}

	private void addToCart(HttpServletRequest req, Cart cart) {
		
		int menuId = Integer.parseInt(req.getParameter("menuId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		
		System.out.println("Bye");
		MenuDAO menudao = new MenuDAOImplementation();
		
		Menu menuItem = menudao.getMenu(menuId);
		
		
		if(menuItem != null) {
			
			CartItem cartItem = new CartItem(menuItem.getMenuId(),
					menuItem.getRestaurantId(),
					menuItem.getItemName(),
					quantity,
					menuItem.getPrice());
			
			cart.addItem(cartItem);
		}
		System.out.println("ok");
	}

	private void updateCart(HttpServletRequest req, Cart cart) {
		
		
	}

	private void removeCart(HttpServletRequest req, Cart cart) {
		
		
	}
}
