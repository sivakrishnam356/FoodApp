package com.tap.model;

public class Restaurant {

	private int restaurantId;
	private String name ;
	private String address;
	private String phone;
	private float rating;
	private String EstimatedTime ;
	private String cuisineType;
	private boolean isActive;
	private String imagePath;
	
	
	
	

	public Restaurant(int restaurantId, String name, String address, String phone, float rating, String estimatedTime,
			String cuisineType, boolean isActive, String imagePath) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.rating = rating;
		EstimatedTime = estimatedTime;
		this.cuisineType = cuisineType;
		this.isActive = isActive;
		this.imagePath = imagePath;
	}
	
	
	
	
	
	public Restaurant() {
		
	}
	
	
	public int getRestaurantId() {
		return restaurantId;
	}
	




	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEstimatedTime() {
		return EstimatedTime;
	}

	public void setEstimatedTime(String estimatedTime) {
		EstimatedTime = estimatedTime;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	

	
	
	
}
