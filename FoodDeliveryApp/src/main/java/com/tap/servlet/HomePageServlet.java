package com.tap.servlet;

import java.io.IOException;
import java.util.List;

import com.tap.daoimplementation.RestaurantDAOImplementation;
import com.tap.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class HomePageServlet extends HttpServlet {
	
	private HttpSession session;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RestaurantDAOImplementation restaurantdao = new RestaurantDAOImplementation();
		
		
		List<Restaurant> restuarants = restaurantdao.getAllRestaurants();
		
		if(restuarants == null) {
			
			resp.getWriter().println("No restaurants found");
			return;
		}
		
		session = req.getSession();
		
		session.setAttribute("restuarants", restuarants);
		
		RequestDispatcher rd = req.getRequestDispatcher("HomePage.jsp");
		rd.forward(req, resp);
		
	}
}
