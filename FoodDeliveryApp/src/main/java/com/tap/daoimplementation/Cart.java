package com.tap.daoimplementation;

import java.util.HashMap;
import java.util.Map;
import com.tap.model.CartItem;
public class Cart {

	
	public Map<Integer,CartItem> cart = new HashMap<>();
	
	public void addItem(CartItem cartItem) {
		
		if(cartItem == null) {
			
			return;
		}
		
		int cartItemId = cartItem.getId();
		
		if(cart.containsKey(cartItemId)) {
			
			CartItem existingItem = cart.get(cartItemId);
			
			existingItem.setQuantity(existingItem.getQuantity() + cartItem.getQuantity());
		}else {
			cart.put(cartItemId, cartItem);
		}
		
		
	}
	
	public void updateItem(int cartItemId , int quantity) {
		
		
		if(cart.containsKey(cartItemId)) {
			
			if(quantity <= 0) {
				cart.remove(cartItemId);
			}else {
				cart.get(cartItemId).setQuantity(quantity);
			}
		}
		
		
		
	}
	
	public void removeItem(int cartItemId) {
		
		cart.remove(cartItemId);
	}
	
	public Map<Integer,CartItem> getItems(){
		
		return cart;
	}
	
	public double getTotalPrice() {
		
		double total = 0.0;
	    for (CartItem item : cart.values()) {
	        total += item.getPrice() * item.getQuantity();
	    }
	    return total;
	}
	
	public void clear() {
		cart.clear();
	}
}
