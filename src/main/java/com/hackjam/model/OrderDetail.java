package com.hackjam.model;

/**
 * @author jun-ho.lee on 2017-06-08.
 */
public class OrderDetail {
	private int orderId;
	private int menuId;
	private int count;
	private boolean hot;

	public OrderDetail(){}
	public OrderDetail(int orderId, int menuId, int count, boolean hot){
		this.orderId = orderId;
		this.menuId = menuId;
		this.count = count;
		this.hot = hot;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
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

	public void setEmpty(){
		menuId=0;
		count=0;
	}
	public boolean isDoneOrder(){
		if(menuId!=0 && count!=0){
			return true;
		}
		return false;
	}
	public boolean isEmpty(){
		if(menuId ==0 && count ==0){
			return true;
		}
		return false;
	}

	public OrderDetail clone(){
		return new OrderDetail(orderId,menuId,count,hot);
	}

	public boolean setFieldsDefault(){
		if(menuId==0){
			return false;
		}
		if(count==0){
			count = 1;
		}
		return true;
	}
}
