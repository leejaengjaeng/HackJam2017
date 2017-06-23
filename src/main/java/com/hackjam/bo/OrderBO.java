package com.hackjam.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hackjam.constant.OrderStatus;
import com.hackjam.dao.MenuDAO;
import com.hackjam.dao.OrderDAO;
import com.hackjam.dao.OrderDetailDAO;
import com.hackjam.model.Employee;
import com.hackjam.model.Menu;
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

	@Autowired
	MenuDAO menuDAO;

	@Transactional(rollbackFor = Exception.class)
	public void addOrder(Order order, String employeeNo) {
		Employee employee = new Employee();
		employee.setEmployeeNo(employeeNo);
		order.setEmployee(employee);
		order.setStatus(OrderStatus.SEND.getStatus());

		orderDAO.insert(order);
		orderDetailDAO.insert(order.getOrderId(), order.getOrderDetails());
	}

	public List<Order> getHistory(String employeeNo) {
		List<Order> orders = orderDAO.selectAllByEmployeeNo(employeeNo);
		setOrderDetails(orders);
		return orders;
	}

	public Map<String, List<Order>> getOrderMap() {
		List<Order> orders = orderDAO.selectAll();
		setOrderDetails(orders);

		Map<String, List<Order>> orderMap = new HashMap<>();
		orderMap.put("send", new ArrayList<>());
		orderMap.put("receive", new ArrayList<>());
		orderMap.put("done", new ArrayList<>());

		for (Order order : orders) {
			int status = order.getStatus();
			if (status == OrderStatus.SEND.getStatus()) {
				orderMap.get("send").add(order);
			} else if (status == OrderStatus.RECEIVE.getStatus()) {
				orderMap.get("receive").add(order);
			} else {
				orderMap.get("done").add(order);
			}
		}

		return orderMap;
	}

	public String confirmMessage(Order order) {
		String message = "";
		List<Menu> menus = menuDAO.selectAll();
		Map<Integer, Menu> menuMap = getMenuMap(menus);

		int totalCost = 0;
		for (OrderDetail orderDetail : order.getOrderDetails()) {
			Menu menu = menuMap.get(orderDetail.getMenu().getMenuId());
			int menuCount = orderDetail.getCount();
			String temperature = orderDetail.isHot() ? "HOT" : "ICE";
			totalCost += menu.getCost() * menuCount;

			message += String.format("%s %s %d잔\n", temperature, menu.getMenuName(), menuCount);
		}

		order.setCost(totalCost);
		message += String.format("%d원", totalCost);
		return message;
	}

	public boolean changeOrderStatus(Order order) {
		return orderDAO.updateOrderStatus(order.getOrderId(), order.getStatus());
	}

	private Map<Integer, Menu> getMenuMap(List<Menu> menus) {
		Map<Integer, Menu> menuMap = new HashMap<>();

		for (Menu menu : menus) {
			menuMap.put(menu.getMenuId(), menu);
		}

		return menuMap;
	}

	private void setOrderDetails(List<Order> orders) {
		int orderCount = orders.size();
		for (int i = 0; i < orderCount; i++) {
			List<OrderDetail> orderDetails = orderDetailDAO.select(orders.get(i).getOrderId());
			orders.get(i).setOrderDetails(orderDetails);
		}
	}
}
