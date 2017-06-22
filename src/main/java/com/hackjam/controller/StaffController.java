package com.hackjam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hackjam.bo.OrderBO;

/**
 * @author jun-ho.lee on 2017-06-08.
 */
@Controller
@RequestMapping("/staff")
public class StaffController {
	@Autowired
	OrderBO orderBO;

	@RequestMapping({"", "/"})
	public ModelAndView main(ModelAndView mav) {
		mav.addObject("orderMap", orderBO.getOrderMap());
		mav.setViewName("/staff/main");
		return mav;
	}
}
