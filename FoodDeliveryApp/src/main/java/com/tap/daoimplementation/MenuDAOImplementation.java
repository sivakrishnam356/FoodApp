package com.tap.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.connection.DBConnection;
import com.tap.dao.MenuDAO;
import com.tap.model.Menu;
import com.tap.model.User;
import com.tap.utility.ConnectionClose;

public class MenuDAOImplementation implements MenuDAO {
	
	
	
	private static final String INSERT_MENU_QUERY = "INSERT into `menu` (`restaurantId`,`itemName`,`price`,`description`,`ratings`,`isAvailable`,`imagePath`)"
			+ "values (?,?,?,?,?,?,?)";
	
	private static final String GET_MENU_BY_ID_QUERY = "SELECT * FROM `menu` WHERE `menu_id` = ?";
	
	private static final String UPDATE_MENU_QUERY = "UPDATE `menu`SET restaurantId = ?,itemName = ?,price = ?,description = ?,ratings = ? , isAvailable = ? , imagePath = ? WHERE `menuId` = ?";	
	
	private static final String DELETE_MENU_QUERY = "DELETE FROM `menu` WHERE `menuId` = ?";
	
	private static final String GET_ALL_MENU_QUERY = "SELECT * FROM `menu` where restaurant_id = ?";
	static Connection connection ;
	static PreparedStatement prepareStatement;

	static  ResultSet result;
	
	
	
	
	

	@Override
	public void addMenu(Menu menu) {
		connection = DBConnection.getConnection();
		
		try {
			prepareStatement = connection.prepareStatement(INSERT_MENU_QUERY);
			
			prepareStatement.setInt(1,menu.getRestaurantId());
			prepareStatement.setString(2, menu.getItemName());
			prepareStatement.setDouble(3, menu.getPrice());
			prepareStatement.setString(4, menu.getDescription());
			prepareStatement.setFloat(5, menu.getRatings());
			prepareStatement.setBoolean(6, menu.isAvailable());
			prepareStatement.setString(7, menu.getImagePath());
			
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionClose.closeResources(connection, prepareStatement, result);
		}
		
	}

	@Override
	public Menu getMenu(int menuId) {
		connection = DBConnection.getConnection();
		
		Menu menu = null ;
		
		try {
			prepareStatement = connection.prepareStatement(GET_MENU_BY_ID_QUERY);
			
			prepareStatement.setInt(1,menuId);
			
			result = prepareStatement.executeQuery();
			
			if(result.next()) {
				menu = extractMenu(result);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionClose.closeResources(connection, prepareStatement, result);
		}
		return menu;
	}

	@Override
	public void updateMenu(Menu menu, int menuId) {
		connection = DBConnection.getConnection();
		
		try {
			prepareStatement = connection.prepareStatement(UPDATE_MENU_QUERY);
			
			prepareStatement.setInt(1, menu.getRestaurantId());
			prepareStatement.setString(2, menu.getItemName());
			prepareStatement.setDouble(3, menu.getPrice());
			prepareStatement.setString(4, menu.getDescription());
			prepareStatement.setFloat(5, menu.getRatings());
			prepareStatement.setBoolean(6, menu.isAvailable());
			prepareStatement.setString(7, menu.getImagePath());
			prepareStatement.setInt(8, menuId);
			
			prepareStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionClose.closeResources(connection, prepareStatement, result);
		}
		
		
	}

	@Override
	public void deleteMenu(int menuId) {
		connection = DBConnection.getConnection();
		
		try {
			prepareStatement = connection.prepareStatement(DELETE_MENU_QUERY);
			
			prepareStatement.setInt(1, menuId);
			
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionClose.closeResources(connection, prepareStatement, result);
		}
		
		
	}

	@Override
	public List<Menu> getAllMenus(int restaurantId) {
		connection = DBConnection.getConnection();
		
		Menu menu = null ;
		
		List<Menu> menusList = new ArrayList<>();
		
		try {
			prepareStatement = connection.prepareStatement(GET_ALL_MENU_QUERY);
			
			prepareStatement.setInt(1, restaurantId);
			
			result = prepareStatement.executeQuery();
			
			while(result.next()) {
				
				menu = extractMenu(result);
				
				menusList.add(menu);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionClose.closeResources(connection, prepareStatement, result);
		}
		
		return menusList;
	}
	
	
	static private Menu extractMenu(ResultSet result) throws SQLException{
		
		Menu menu = new Menu(
		        result.getInt("menu_id"),
		        result.getInt("restaurant_id"),
		        result.getString("item_name"),
		        result.getString("description"),
		        result.getDouble("price"),
		        result.getFloat("ratings"),
		        result.getBoolean("isAvailable"),
		        result.getString("image_path"));
		return menu;
	}


	
}
