package com.hackjam.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackjam.dao.CustomerCountDAO;

/**
 * @author jun-ho.lee on 2017-06-23.
 */
@Service
public class CafeStatusBO {
	@Autowired
	CustomerCountDAO customerCountDAO;

	public String getCafeStatus() {
		int count = customerCountDAO.select();

		String status;
		if (count >= 30) {
			status = "busy";
		} else if (count >= 15) {
			status = "normal";
		} else {
			status = "spare";
		}

		return status;
	}
}
