package com.tap.main;
import com.tap.model.*;

import java.sql.Date;
import java.util.List;

import com.tap.daoimplementation.*;
public class Main {

	public static void main(String[] args) {
		
		OrderDAOImplementation o = new OrderDAOImplementation();
		
//		o.addOrder(order);
		
		o.deleteOrder(7);
		o.deleteOrder(8);
		
				
		
		
	}
}
