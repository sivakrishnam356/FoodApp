package com.tap.servlet;

import java.io.IOException;
import com.tap.daoimplementation.UserDAOImplementation;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class LoginServlet extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		if(email == null || password == null) {
			resp.getWriter().println("Invalid email or password");
			return ;
		}
		
		UserDAOImplementation userdao = new UserDAOImplementation();
		
		User user = userdao.getUser(email);
		
		if(user == null) {
			
			resp.getWriter().println("Email is not present");
			return;
		}
		
		String userPassword = user.getPassword();
		
		if(userPassword.equals(password)) {
			
			resp.sendRedirect("HomePageServlet");
		}else {
			resp.getWriter().println("Invalid Password");
			return;
		}
		
		
		
		
	}
}
