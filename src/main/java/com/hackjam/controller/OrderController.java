package com.hackjam.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hackjam.bo.OrderBO;
import com.hackjam.model.Order;
import com.hackjam.util.CookieUtils;

/**
 * @author jun-ho.lee on 2017-06-21.
 */
@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderBO orderBO;

	@Autowired
	CookieUtils cookieUtils;

	@RequestMapping({"", "/"})
	public ModelAndView order(HttpServletRequest request, Order order, ModelAndView mav) {
		String employeeNo = cookieUtils.getEmployeeNoByCookies(request.getCookies());
		orderBO.addOrder(order, employeeNo);
		mav.setViewName("redirect:/customer");
		return mav;
	}
}