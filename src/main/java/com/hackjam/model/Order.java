package com.hackjam.model;

import java.util.Date;
import java.util.List;

import com.hackjam.util.DateUtils;

/**
 * Created by naver on 2017. 6. 18..
 */
public class Order {
	List<OrderedBeverage> orderList;
	private List<OrderDetail> orderDetails;
	private int orderId;
	private Employee employee;
	private int cost;
	private Date orderYmdt;
	private Date finishYmdt;
	private int status;

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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getOrderYmdt() {
		return DateUtils.formatYmdt(orderYmdt);
	}

	public void setOrderYmdt(Date orderYmdt) {
		this.orderYmdt = orderYmdt;
	}

	public String getFinishYmdt() {
		return DateUtils.formatYmdt(finishYmdt);
	}

	public void setFinishYmdt(Date finishYmdt) {
		this.finishYmdt = finishYmdt;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
