package com.hackjam.model;

/**
 * @author jun-ho.lee on 2017-06-08.
 */
public class OrderDetail {
	private int orderId;
	private Menu menu;
	private int count;
	private boolean hot;

	public OrderDetail(){}
	public OrderDetail(int orderId, Menu menu, int count, boolean hot){
		this.orderId = orderId;
		this.menu = menu;
		this.count = count;
		this.hot = hot;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isHot() {
		return hot;
	}

	public void setHot(boolean hot) {
		this.hot = hot;
	}

	public void setMenuId(int menuId) {
		if (menu == null) {
			menu = new Menu();
		}
		menu.setMenuId(menuId);
	}

	public int getMenuId(){
		if(menu == null){
			return 0;
		}
		return menu.getMenuId();
	}

	public void setMenuName(String menuName) {
		if (menu == null) {
			menu = new Menu();
		}
		menu.setMenuName(menuName);
	}

	public void setEmpty(){
		menu=null;
		count=0;
	}

	public boolean isDoneOrder(){
		if(menu !=null && count!=0){
			return true;
		}
		return false;
	}
	public boolean isEmpty(){
		if(menu ==null && count ==0){
			return true;
		}
		return false;
	}

	public OrderDetail clone(){
		return new OrderDetail(orderId,menu.clone(),count,hot);
	}


	public boolean setFieldsDefault(){
		if(menu==null){
			return false;
		}
		if(count==0){
			count = 1;
		}
		return true;
	}

}
