package com.hackjam.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hackjam.dao.OrderDAO;
import com.hackjam.dao.OrderDetailDAO;
import com.hackjam.model.Order;
import com.hackjam.model.OrderDetail;

/**
 * @author jun-ho.lee on 2017-06-08.
 */
@Service
public class OrderBO {
	@Autowired
	OrderDAO orderDAO;

	@Autowired
	OrderDetailDAO orderDetailDAO;

	@Transactional(rollbackFor = Exception.class)
	public void addOrder(Order order, String employeeNo) {
		order.setEmployeeNo(employeeNo);
		order.setOrderStatus(0);

		orderDAO.insert(order);
		orderDetailDAO.insert(order.getOrderId(), order.getOrderDetails());
	}

	public List<Order> getHistory(String employeeNo) {
		return orderDAO.selectAll(employeeNo);
	}

	public List<OrderDetail> getOrderDetail(int orderId) {
		return orderDetailDAO.select(orderId);
	}

	public boolean changeOrderStatus(Order order) {
		return orderDAO.updateOrderStatus(order.getOrderId(), order.getOrderStatus());
	}
}
