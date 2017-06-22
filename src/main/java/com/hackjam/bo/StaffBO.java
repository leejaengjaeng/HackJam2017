package com.hackjam.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackjam.dao.StaffDAO;

/**
 * @author jun-ho.lee on 2017-06-12.
 */
@Service
public class StaffBO {
	@Autowired
	StaffDAO staffDAO;

	public boolean add(String employeeNo) {
		return staffDAO.insert(employeeNo);
	}

	public boolean delete(String employeeNo) {
		return staffDAO.delete(employeeNo);
	}
}
