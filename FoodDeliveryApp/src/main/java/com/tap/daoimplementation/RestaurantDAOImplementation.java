package com.tap.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.connection.DBConnection;
import com.tap.dao.RestaurantDAO;
import com.tap.model.Restaurant;
import com.tap.model.User;
import com.tap.utility.ConnectionClose;

public class RestaurantDAOImplementation implements RestaurantDAO {
	
	private static final String INSRET_RESTAURANT_QUERY = "INSERT INTO `Restaurant` (`name`,`address`,`phone`,`rating`,`cuisineType`,`isActive`,`ETA`,`adminUserId`,`imagePath`)"
			+ "values (?,?,?,?,?,?,?,?,?)";
	
	private static final String GET_RESTAURANT_BY_ID_QUERY = "SELECT * FROM `restaurant` WHERE `restaurant_id` = ?";
	
	private static final String UPDATE_RESTAURANT_QUERY = "UPDATE `user` SET name = ? ,address = ? ,phone = ? ,rating = ? ,cusineType = ? , isActive = ? , imagePath = ? where `userId` = ?";	
	
	private static final String DELETE_RESTAURANT_QUERY = "DELETE FROM `restaurant` WHERE `restaurantId` = ?";
	
	private static final String GET_ALL_RESTAURANTS_QUERY = "SELECT * FROM `restaurant`";
	static Connection connection ;
	static PreparedStatement prepareStatement;

	static  ResultSet result;

	@Override
	public void addRestaurant(Restaurant restaurant) {
connection = DBConnection.getConnection();
		
		try {
			prepareStatement = connection.prepareStatement(INSRET_RESTAURANT_QUERY);
			
			prepareStatement.setString(1,restaurant.getName());
			prepareStatement.setString(2, restaurant.getAddress());
			prepareStatement.setString(3, restaurant.getPhone());
			prepareStatement.setFloat(4, restaurant.getRating());
			prepareStatement.setString(5, restaurant.getCuisineType());
			prepareStatement.setBoolean(6, restaurant.isActive());
			prepareStatement.setString(7, restaurant.getEstimatedTime());
			
			prepareStatement.setString(9, restaurant.getImagePath());
			
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionClose.closeResources(connection, prepareStatement, result);
		}
		
	}

	@Override
	public Restaurant getRestaurant(int restaurantId) {
		
		connection = DBConnection.getConnection();
		
		Restaurant restaurant = null;
		
		try {
			prepareStatement = connection.prepareStatement(GET_RESTAURANT_BY_ID_QUERY);
			
			prepareStatement.setInt(1,restaurantId);
			
			result = prepareStatement.executeQuery();
			
			if(result.next()) {
				restaurant = extractRestaurant(result);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionClose.closeResources(connection, prepareStatement, result);
		}
		return restaurant;
	}

	@Override
	public void updateRestaurant(Restaurant restaurant, int restaurantId) {
		connection = DBConnection.getConnection();
		
		try {
			prepareStatement = connection.prepareStatement(UPDATE_RESTAURANT_QUERY);
			
			prepareStatement.setString(1, restaurant.getName());
			prepareStatement.setString(2, restaurant.getPhone());
			prepareStatement.setString(2, restaurant.getAddress());
			prepareStatement.setFloat(2, restaurant.getRating());
			prepareStatement.setString(2, restaurant.getCuisineType());
			prepareStatement.setBoolean(2, restaurant.isActive());
			prepareStatement.setString(2, restaurant.getImagePath());
			
			
			prepareStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionClose.closeResources(connection, prepareStatement, result);
		}
		
	}

	@Override
	public void deleteRestaurant(int restaurantId) {
		connection = DBConnection.getConnection();
		
		try {
			prepareStatement = connection.prepareStatement(DELETE_RESTAURANT_QUERY);
			
			prepareStatement.setInt(1, restaurantId);
			
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionClose.closeResources(connection, prepareStatement, result);
		}
		
		
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		
		connection = DBConnection.getConnection();
		
		Restaurant restaurant = null ;
		
		List<Restaurant> restaurantsList = new ArrayList<>();
		
		try {
			prepareStatement = connection.prepareStatement(GET_ALL_RESTAURANTS_QUERY);
			
			
			result = prepareStatement.executeQuery();
			
			while(result.next()) {
				
				restaurant = extractRestaurant(result);
				
				restaurantsList.add(restaurant);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionClose.closeResources(connection, prepareStatement, result);
		}
		
		return restaurantsList;
	}
	
	Restaurant extractRestaurant(ResultSet result) throws SQLException {
		
		return new Restaurant(
                result.getInt("restaurant_id"),     
                result.getString("restaurant_name"),          
                result.getString("restaurant_address"),       
                result.getString("phone_number"),  
                result.getFloat("rating"),  
                result.getString("EST"),
                result.getString("cuisine_type"),  
                result.getBoolean("isactive"),
                result.getString("image_path"));
	}

	
}
