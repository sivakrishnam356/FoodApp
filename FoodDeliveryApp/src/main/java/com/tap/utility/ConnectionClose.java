package com.tap.utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConnectionClose {
	
	static public void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
	    try {
	        if (resultSet != null) {
	            resultSet.close();
	        }
	        if (preparedStatement != null) {
	            preparedStatement.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


}
