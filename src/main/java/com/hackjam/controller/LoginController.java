package com.hackjam.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hackjam.dao.StaffDAO;
import com.hackjam.util.CookieUtils;

/**
 * @author jun-ho.lee on 2017-06-08.
 */
@Controller
public class LoginController {
	@Autowired
	CookieUtils cookieUtils;

	@Autowired
	StaffDAO staffDAO;

	@RequestMapping({"", "/"})
	public String loginPage() {
		return "login";
	}

	@RequestMapping("/login")
	public String login(String employeeNo, HttpServletResponse response) {
		Cookie loginCookie = cookieUtils.getLoginCookie(employeeNo);
		response.addCookie(loginCookie);

		if (staffDAO.isExists(employeeNo)) {
			return "redirect:/staff";
		} else {
			return "redirect:/customer";
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpServletResponse response) {
		Cookie logoutCookie = cookieUtils.getLogoutCookie();
		response.addCookie(logoutCookie);
		return "redirect:/";
	}
}
