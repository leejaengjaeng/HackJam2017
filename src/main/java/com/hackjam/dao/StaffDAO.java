package com.hackjam.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author jun-ho.lee on 2017-06-08.
 */
@Mapper
public interface StaffDAO {
	boolean isExists(String employeeNo);

	boolean insert(String employeeNo);

	boolean delete(String employeeNo);
}
