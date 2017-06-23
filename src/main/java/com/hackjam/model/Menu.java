package com.hackjam.model;

import com.hackjam.constant.MenuType;

/**
 * @author jun-ho.lee on 2017-06-08.
 */
public class Menu {
	private int menuId;
	private String menuName;
	private int cost;
	private String imageName;
	private MenuType menuType;
	private boolean onSale;
	private String sameNamesWithSeperator;
	private String defaultTemperature;

	public static String NAME_SEPERATOR = ";";

	public Menu(){}

	public Menu(int menuId, String menuName, int cost, String imageName, MenuType menuType) {
		this.menuId = menuId;
		this.menuName = menuName;
		this.cost = cost;
		this.imageName = imageName;
		this.menuType = menuType;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public MenuType getMenuType() {
		return menuType;
	}

	public void setMenuType(MenuType menuType) {
		this.menuType = menuType;
	}

	public String getDefaultTemperature() {
		return defaultTemperature;
	}

	public boolean isOnSale() {
		return onSale;
	}

	public void setOnSale(boolean onSale) {
		this.onSale = onSale;
	}

	public String getSameNamesWithSeperator() {
		return sameNamesWithSeperator;
	}

	public void setSameNamesWithSeperator(String sameNamesWithSeperator) {
		this.sameNamesWithSeperator = sameNamesWithSeperator;
	}

	public static String getNameSeperator() {
		return NAME_SEPERATOR;
	}

	public static void setNameSeperator(String nameSeperator) {
		NAME_SEPERATOR = nameSeperator;
	}

	public void setDefaultTemperature(String defaultTemperature) {
		this.defaultTemperature = defaultTemperature;
	}

	public Menu clone(){
		return new Menu(menuId,menuName,cost,imageName,menuType);
	}
}
