package com.hackjam.dao;

import org.apache.ibatis.annotations.Mapper;

import com.hackjam.model.Employee;

/**
 * @author jun-ho.lee on 2017-06-08.
 */
@Mapper
public interface EmployeeDAO {
	Employee select(String employeeNo);
}
