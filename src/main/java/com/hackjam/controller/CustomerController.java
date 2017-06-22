package com.hackjam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hackjam.bo.MenuBO;

/**
 * @author jun-ho.lee on 2017-06-08.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	MenuBO menuBO;

	@RequestMapping({"", "/"})
	public ModelAndView main(ModelAndView mav) {
		mav.addObject("menuMap", menuBO.getMenuMap());
		mav.setViewName("/customer/main");
		return mav;
	}
}
