package com.tap.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import com.tap.connection.DBConnection;
import com.tap.dao.OrderDAO;
import com.tap.model.Order;
import com.tap.model.User;
import com.tap.utility.ConnectionClose;

public class OrderDAOImplementation implements OrderDAO {

	
	private static final String INSRET_ORDER_QUERY = "INSERT into `orders` (`userEmail`,`restaurantId`,`orderDate`,`totalAmount`,`status`,`paymentMode`)"
			+ "values (?,?,?,?,?,?)";
	
	private static final String GET_ORDER_BY_ID_QUERY = "SELECT * FROM `orders` WHERE `orderId` = ?";
	
	private static final String UPDATE_ORDER_QUERY = "UPDATE `orders` SET userId = ?,restaurantId = ?,totalAmount = ?, orderDate = ? ,status = ?,paymentMode = ? WHERE `orderID` = ?";	
	
	private static final String DELETE_ORDER_QUERY = "DELETE FROM `orders` WHERE `orderId` = ?";
	
	private static final String GET_ALL_ORDERS_QUERY = "SELECT * FROM `orders`";
	static Connection connection ;
	static PreparedStatement prepareStatement;

	static  ResultSet result;

	@Override
	public int addOrder(Order order) {
		connection = DBConnection.getConnection();
		int orderId = 0;
		try {
			//prepareStatement = connection.prepareStatement(INSRET_ORDER_QUERY);
			
			prepareStatement = connection.prepareStatement(INSRET_ORDER_QUERY,Statement.RETURN_GENERATED_KEYS);
			
			prepareStatement.setString(1,order.getEmail());
			prepareStatement.setInt(2, order.getRestuarantId());
			prepareStatement.setDate(3, (Date) order.getOrderDate());
			prepareStatement.setDouble(4, order.getTotalAmount());
			prepareStatement.setString(5, order.getStatus());
			prepareStatement.setString(6, order.getPaymentMode());
			
			
			prepareStatement.executeUpdate();
			ResultSet generatedKeys = prepareStatement.getGeneratedKeys();
			
			while(generatedKeys.next()) {
				orderId = generatedKeys.getInt(1);
			}
			
	
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionClose.closeResources(connection, prepareStatement, result);
		}
		
		return orderId;
		
	}

	@Override
	public Order getOrder(int orderId) {
		connection = DBConnection.getConnection();
		
		Order order = null ;
		
		try {
			prepareStatement = connection.prepareStatement(GET_ORDER_BY_ID_QUERY);
			
			prepareStatement.setInt(1,orderId);
			
			result = prepareStatement.executeQuery();
			
			order = extractOrder(result);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionClose.closeResources(connection, prepareStatement, result);
		}
		return order;
	}

	@Override
	public void updateOrder(Order order, int orderId) {
		connection = DBConnection.getConnection();
		
		try {
			prepareStatement = connection.prepareStatement(UPDATE_ORDER_QUERY);
			
			prepareStatement.setString(1, order.getEmail());
			prepareStatement.setInt(2, order.getRestuarantId());
			prepareStatement.setDouble(3, order.getTotalAmount());
			prepareStatement.setDate(4, (Date) order.getOrderDate());
			prepareStatement.setString(5, order.getStatus());
			prepareStatement.setString(6, order.getPaymentMode());
			prepareStatement.setInt(7, orderId);
			
			prepareStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionClose.closeResources(connection, prepareStatement, result);
		}
		
		
	}

	@Override
	public void deleteOrder(int orderId) {
		connection = DBConnection.getConnection();
		
		try {
			prepareStatement = connection.prepareStatement(DELETE_ORDER_QUERY);
			
			prepareStatement.setInt(1, orderId);
			
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionClose.closeResources(connection, prepareStatement, result);
		}
		
	}

	@Override
	public List<Order> getAllOrders() {
		connection = DBConnection.getConnection();
		
		Order order = null ;
		
		List<Order> ordersList = new ArrayList<>();
		
		try {
			prepareStatement = connection.prepareStatement(GET_ALL_ORDERS_QUERY);
			
			
			result = prepareStatement.executeQuery();
			
			while(result.next()) {
				
				order = extractOrder(result);
				
				ordersList.add(order);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ConnectionClose.closeResources(connection, prepareStatement, result);
		}
		
		return ordersList;
	}
	
	static private Order extractOrder(ResultSet result) throws SQLException{
		
		Order order = new Order(
				result.getInt("orderId"),
				result.getString("userEmail"),
				result.getInt("restaurantId"),
				result.getDate("orderDate"),
				result.getDouble("totalAmount"),
				result.getString("status"),
				result.getString("paymentMode"));
				
		return order;
	}
	
	
}
