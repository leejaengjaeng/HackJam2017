package com.hackjam.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hackjam.model.OrderDetail;

/**
 * @author jun-ho.lee on 2017-06-08.
 */
@Mapper
public interface OrderDetailDAO {
	List<OrderDetail> select(int orderId);

	boolean insert(@Param("orderId") int orderId, @Param("orderDetails") List<OrderDetail> orderDetails);

	/* 주문 변경이나 삭제가 가능하면 사용
	boolean delete(OrderDetail orderDetail);

	boolean deleteByOrderId(int orderId);*/
}
