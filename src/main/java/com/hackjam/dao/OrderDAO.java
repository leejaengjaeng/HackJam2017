package com.hackjam.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hackjam.model.Order;

/**
 * @author jun-ho.lee on 2017-06-08.
 */
@Mapper
public interface OrderDAO {
	Order select(int orderId);

	List<Order> selectAll(String employeeNo);

	boolean insert(Order order);

	/* 주문 변경이나 삭제가 가능하면 사용
	boolean delete(int orderId);

	boolean update(Order order);
	*/

	boolean updateOrderStatus(@Param("orderId") int orderId, @Param("status") int status);
}
