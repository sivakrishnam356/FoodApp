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

	
	private static final String INSERT_ORDERITEM_QUERY = "INSERT into `orderitem` (`orderId`,`menuId`,`quantity`,`totalPrice`)"
			+ "values (?,?,?,?)";
	
	private static final String GET_ORDERITEM_BY_ID_QUERY = "SELECT * FROM `orderitem` WHERE `orderItemId` = ?";
	
	private static final String UPDATE_ORDERITEM_QUERY = "UPDATE `orderitem` SET orderId = ?,menuId = ?,quantity = ?, totalPrice = ?  WHERE `orderItemID` = ?";	
	
	private static final String DELETE_ORDERITEM_QUERY = "DELETE FROM `orderitem` WHERE `orderItemId` = ?";
	
	private static final String GET_ALL_ORDERITEMS_QUERY = "SELECT * FROM `orderitem`";
	static Connection connection ;
	static PreparedStatement prepareStatement;

	static  ResultSet result;
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void addOrderItem(OrderItem orderItem) {
		connection = DBConnection.getConnection();
		
		try {
			prepareStatement = connection.prepareStatement(INSERT_ORDERITEM_QUERY);
			
			prepareStatement.setInt(1,orderItem.getOrderId());
			prepareStatement.setInt(2, orderItem.getMenuId());
			prepareStatement.setInt(3, orderItem.getQuantity());
			prepareStatement.setDouble(4, orderItem.getTotalPrice());
			
			
			
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
			
			orderItem = extractOrderItem(result);
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
			prepareStatement.setInt(3, orderItem.getQuantity());
			prepareStatement.setDouble(4, orderItem.getTotalPrice());
			prepareStatement.setInt(5, orderItemId);
			
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
	public List<OrderItem> getAllOrderItems() {
		connection = DBConnection.getConnection();
		
		OrderItem orderItem = null ;
		
		List<OrderItem> orderItemsList = new ArrayList<>();
		
		try {
			prepareStatement = connection.prepareStatement(GET_ALL_ORDERITEMS_QUERY);
			
			
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
				result.getInt("menuId"),
				result.getInt("quantity"),
				result.getDouble("totalPrice"));
				
				
		return orderItem;
	}
	
	
	

}
