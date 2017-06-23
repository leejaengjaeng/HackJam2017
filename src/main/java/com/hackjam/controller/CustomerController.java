package com.hackjam.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hackjam.bo.CafeStatusBO;
import com.hackjam.bo.MenuBO;
import com.hackjam.bo.OrderBO;
import com.hackjam.util.CookieUtils;

/**
 * @author jun-ho.lee on 2017-06-08.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	MenuBO menuBO;

	@Autowired
	CafeStatusBO cafeStatusBO;

	@Autowired
	OrderBO orderBO;

	@Autowired
	CookieUtils cookieUtils;

	@RequestMapping({"", "/"})
	public ModelAndView main(ModelAndView mav) {
		mav.addObject("menuMap", menuBO.getMenuMap());
		mav.addObject("cafeStatus", cafeStatusBO.getCafeStatus());
		mav.setViewName("/customer/main");
		return mav;
	}

	@RequestMapping("/orderHistory")
	public ModelAndView oderHistory(HttpServletRequest request, ModelAndView mav) {
		String employeeNo = cookieUtils.getEmployeeNoByCookies(request.getCookies());
		mav.addObject("orders", orderBO.getHistory(employeeNo));
		mav.setViewName("/customer/history");
		return mav;
	}
}
