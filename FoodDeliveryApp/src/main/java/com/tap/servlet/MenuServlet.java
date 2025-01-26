package com.tap.servlet;

import java.io.IOException;
import java.util.List;

import com.tap.daoimplementation.MenuDAOImplementation;
import com.tap.model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



public class MenuServlet extends HttpServlet {
	
	private HttpSession session;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
		 
		 MenuDAOImplementation menudao = new MenuDAOImplementation();
		 
		 List<Menu> menus = menudao.getAllMenus(restaurantId);
		 
		 if(menus == null) {
			 
			 resp.getWriter().println("menus are not found");
		 }
		 
		 session = req.getSession();
		 
		 session.setAttribute("allMenus", menus);
		 
		 RequestDispatcher rd = req.getRequestDispatcher("Menu.jsp");
		 rd.forward(req, resp);
		 
	}

}
