package com.tap.servlet;

import java.io.IOException;
import com.tap.daoimplementation.UserDAOImplementation;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class RegisterServlet extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirm-password");
		
		
		if(!password.equals(confirmPassword)) {
			resp.getWriter().println("password and confirmPassword are not correct");
		}
		
		User user = new User(name , email , phone , password , confirmPassword);
		
		UserDAOImplementation userdao = new UserDAOImplementation();
		
		int x = userdao.addUser(user);
		
		if(x == 1) {
			
			resp.getWriter().println("succees");
		}else {
			resp.getWriter().println("fail");
		}
	}

}
