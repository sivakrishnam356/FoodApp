package com.tap.daoimplementation;

import com.tap.utility.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.tap.connection.DBConnection;
import com.tap.dao.UserDAO;
import com.tap.model.User;

public class UserDAOImplementation implements UserDAO {
	
	
	private static final String INSERT_USER_QUERY = "INSERT into `register` (`full_name`,`email_address`,`phone_number`,`password`,`confirm_password`)"
			+ "values (?,?,?,?,?)";
	
	private static final String GET_USER_BY_ID_QUERY = "SELECT * FROM `register` WHERE `email_address` = ?";
	
	private static final String UPDATE_USER_QUERY = "UPDATE `register`SET full_name = ?,email_address = ?,phone_number = ?,password = ?,confirm_password = ? WHERE `email_address` = ?";	
	
	private static final String DELETE_USER_QUERY = "DELETE FROM `register` WHERE `email_address` = ?";
	
	private static final String GET_ALL_USERS_QUERY = "SELECT * FROM `register`";
	static Connection connection ;
	static PreparedStatement prepareStatement;

	static  ResultSet result;
	@Override
	public int addUser(User user) {
		
		connection = DBConnection.getConnection();
		int x = 0;
		try {
			prepareStatement = connection.prepareStatement(INSERT_USER_QUERY);
			
			prepareStatement.setString(1,user.getName());
			prepareStatement.setString(2, user.getEmail());
			prepareStatement.setString(3, user.getPhone());
			prepareStatement.setString(4, user.getPassword());
			prepareStatement.setString(5, user.getConfirmPassword());
			
			
			 x = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			ConnectionClose.closeResources(connection, prepareStatement, result);
		}
		
		return x;
	}

	@Override
	public User getUser(String userEmail) {
		
		connection = DBConnection.getConnection();
		
		User user = null ;
		
		try {
			prepareStatement = connection.prepareStatement(GET_USER_BY_ID_QUERY);
			
			prepareStatement.setString(1,userEmail);
			
			result = prepareStatement.executeQuery();
			
			if (result.next()) {
	            user = extractUser(result);
	        }
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			ConnectionClose.closeResources(connection, prepareStatement, result);
		}
		return user;
	}

	@Override
	public void updateUser(User user , int userId) {
		
		connection = DBConnection.getConnection();
		
		try {
			prepareStatement = connection.prepareStatement(UPDATE_USER_QUERY);
			
			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getEmail());
			prepareStatement.setString(3, user.getPhone());
			prepareStatement.setString(4, user.getPassword());
			prepareStatement.setString(5, user.getConfirmPassword());
			
			
			prepareStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionClose.closeResources(connection, prepareStatement, result);
		}
		
		
		
	}

	@Override
	public void deleteUser(int userId) {
		
		connection = DBConnection.getConnection();
		
		try {
			prepareStatement = connection.prepareStatement(DELETE_USER_QUERY);
			
			prepareStatement.setInt(1, userId);
			
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public List<User> getAllUsers() {
		connection = DBConnection.getConnection();
		
		User user = null ;
		
		List<User> usersList = new ArrayList<>();
		
		try {
			prepareStatement = connection.prepareStatement(GET_ALL_USERS_QUERY);
			
			
			result = prepareStatement.executeQuery();
			
			while(result.next()) {
				
				user = extractUser(result);
				
				usersList.add(user);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionClose.closeResources(connection, prepareStatement, result);
		}
		
		return usersList;
	}
	
	
	static private User extractUser(ResultSet result) throws SQLException{
		
		User user = new User(
				
				result.getString("full_name"),
				
				result.getString("email_address"),
				result.getString("phone_number"),
				result.getString("password"),
				result.getString("confirm_password"));
		return user;
	}

	
}
