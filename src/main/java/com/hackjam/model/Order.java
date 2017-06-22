package com.hackjam.model;

import java.util.Date;
import java.util.List;

/**
 * Created by naver on 2017. 6. 18..
 */
public class Order {
	List<OrderedBeverage> orderList;
	private List<OrderDetail> orderDetails;
	private int orderId;
	private String employeeNo;
	private int cost;
	private Date orderYmdt;
	private Date finishYmdt;
	private int orderStatus;

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Date getOrderYmdt() {
		return orderYmdt;
	}

	public void setOrderYmdt(Date orderYmdt) {
		this.orderYmdt = orderYmdt;
	}

	public Date getFinishYmdt() {
		return finishYmdt;
	}

	public void setFinishYmdt(Date finishYmdt) {
		this.finishYmdt = finishYmdt;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
}
