package com.tap.dao;

import java.util.List;

import com.tap.model.Menu;


public interface MenuDAO {

	void addMenu(Menu menu);
	Menu getMenu(int menuId);
	void updateMenu(Menu menu,int menuId);
	void deleteMenu(int menuId);
	List<Menu> getAllMenus(int restaurantId);
}
