package com.hackjam.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		order.getEmployee().setEmployeeNo(employeeNo);
		order.setDone(false);

		orderDAO.insert(order);
		orderDetailDAO.insert(order.getOrderId(), order.getOrderDetails());
	}

	public List<Order> getHistory(String employeeNo) {
		return orderDAO.selectAllByEmployeeNo(employeeNo);
	}

	public Map<Boolean, List<Order>> getOrderMap() {
		List<Order> orders = orderDAO.selectAll();
		setOrderDetails(orders);

		Map<Boolean, List<Order>> orderMap = new HashMap<>();
		orderMap.put(true, new ArrayList<>());
		orderMap.put(false, new ArrayList<>());

		for (Order order : orders) {
			orderMap.get(order.isDone()).add(order);
		}

		return orderMap;
	}

	public boolean changeOrderStatus(Order order) {
		return orderDAO.updateOrderStatus(order.getOrderId(), order.isDone());
	}

	private void setOrderDetails(List<Order> orders) {
		int orderCount = orders.size();
		for (int i = 0; i < orderCount; i++) {
			List<OrderDetail> orderDetails = orderDetailDAO.select(orders.get(i).getOrderId());
			orders.get(i).setOrderDetails(orderDetails);
		}
	}
}
