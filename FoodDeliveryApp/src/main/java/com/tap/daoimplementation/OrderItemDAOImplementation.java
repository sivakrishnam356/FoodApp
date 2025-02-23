package com.tap.daoimplementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.connection.DBConnection;
import com.tap.dao.OrderItemDAO;
import com.tap.model.Order;
import com.tap.model.OrderItem;
import com.tap.utility.ConnectionClose;

public class OrderItemDAOImplementation implements OrderItemDAO{

	
	private static final String INSERT_ORDERITEM_QUERY = "INSERT into `orderitem` (`orderId`,`menuId`,`orderName`,`quantity`,`price`)"
			+ "values (?,?,?,?,?)";
	
	private static final String GET_ORDERITEM_BY_ID_QUERY = "SELECT * FROM `orderitem` WHERE `orderItemId` = ?";
	
	private static final String UPDATE_ORDERITEM_QUERY = "UPDATE `orderitem` SET orderId = ?,menuId = ?, orderName = ? , quantity = ?, totalPrice = ?  WHERE `orderItemID` = ?";	
	
	private static final String DELETE_ORDERITEM_QUERY = "DELETE FROM `orderitem` WHERE `orderItemId` = ?";
	
	private static final String GET_ALL_ORDERITEMS_QUERY = "SELECT * FROM `orderitem` where orderId = ?";
	static Connection connection ;
	static PreparedStatement prepareStatement;

	static  ResultSet result;
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void addOrderItem(OrderItem orderItem) {
		connection = DBConnection.getConnection();
		
		System.out.println(orderItem.getOrderName());
		
		try {
			prepareStatement = connection.prepareStatement(INSERT_ORDERITEM_QUERY);
			
			prepareStatement.setInt(1,orderItem.getOrderId());
			prepareStatement.setInt(2, orderItem.getMenuId());
			prepareStatement.setString(3, orderItem.getOrderName());
			
			prepareStatement.setInt(4, orderItem.getQuantity());
			prepareStatement.setDouble(5, orderItem.getPrice());
			
			
			
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionClose.closeResources(connection, prepareStatement, result);
		}
		
	}

	@Override
	public OrderItem getOrderItem(int orderItemId) {
		connection = DBConnection.getConnection();
		
		OrderItem orderItem = null ;
		
		try {
			prepareStatement = connection.prepareStatement(GET_ORDERITEM_BY_ID_QUERY);
			
			prepareStatement.setInt(1,orderItemId);
			
			result = prepareStatement.executeQuery();
			
			if(result.next()) {
				orderItem = extractOrderItem(result);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionClose.closeResources(connection, prepareStatement, result);
		}
		return orderItem;
	}

	@Override
	public void updateOrderItem(OrderItem orderItem, int orderItemId) {
		connection = DBConnection.getConnection();
		
		try {
			prepareStatement = connection.prepareStatement(UPDATE_ORDERITEM_QUERY);
			
			prepareStatement.setInt(1, orderItem.getOrderId());
			prepareStatement.setInt(2, orderItem.getMenuId());
			prepareStatement.setString(3, orderItem.getOrderName());
			
			prepareStatement.setInt(4, orderItem.getQuantity());
			prepareStatement.setDouble(5, orderItem.getPrice());
			prepareStatement.setInt(6, orderItemId);
			
			prepareStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionClose.closeResources(connection, prepareStatement, result);
		}
		
		
	}

	@Override
	public void deleteOrderItem(int orderItemId) {
		connection = DBConnection.getConnection();
		
		try {
			prepareStatement = connection.prepareStatement(DELETE_ORDERITEM_QUERY);
			
			prepareStatement.setInt(1, orderItemId);
			
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionClose.closeResources(connection, prepareStatement, result);
		}
		
	}

	@Override
	public List<OrderItem> getAllOrderItems(int orderId) {
		connection = DBConnection.getConnection();
		
		OrderItem orderItem = null ;
		
		List<OrderItem> orderItemsList = new ArrayList<>();
		
		try {
			prepareStatement = connection.prepareStatement(GET_ALL_ORDERITEMS_QUERY);
			
			
			prepareStatement.setInt(1, orderId);
			
			result = prepareStatement.executeQuery();
			
			while(result.next()) {
				
				orderItem = extractOrderItem(result);
				
				orderItemsList.add(orderItem);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionClose.closeResources(connection, prepareStatement, result);
		}
		
		return orderItemsList;
	}
	
	
	
	
	
	
	
	
	
	static private OrderItem extractOrderItem(ResultSet result) throws SQLException{
		
		OrderItem orderItem = new OrderItem(
				result.getInt("orderItemId"),
				result.getInt("orderId"),
				result.getString("orderName"),
				result.getInt("menuId"),
				result.getInt("quantity"),
				result.getDouble("price"));
				
				
		return orderItem;
	}
	
	
	

}
