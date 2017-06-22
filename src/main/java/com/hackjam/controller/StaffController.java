package com.hackjam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jun-ho.lee on 2017-06-08.
 */
@Controller
@RequestMapping("staff")
public class StaffController {

	@RequestMapping({"", "/"})
	public String main() {
		return "/staff/main";
	}
}
