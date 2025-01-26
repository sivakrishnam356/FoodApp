package com.tap.main;
import com.tap.model.*;

import java.sql.Date;
import java.util.List;

import com.tap.daoimplementation.*;
public class Main {

	public static void main(String[] args) {
		
		Date today = new Date(0, 0, 0);
		Order order = new Order(1000,1,1,today,1900,"delivered","UPI");
		
		OrderDAOImplementation o = new OrderDAOImplementation();
		
//		o.addOrder(order);
		
		o.deleteOrder(7);
		o.deleteOrder(8);
		
				
		
		
	}
}
