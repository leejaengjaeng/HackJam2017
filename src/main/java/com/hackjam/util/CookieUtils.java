package com.hackjam.util;

import javax.servlet.http.Cookie;

import org.springframework.stereotype.Component;

/**
 * @author jun-ho.lee on 2017-06-08.
 */
@Component
public class CookieUtils {
	private final String EMPLOYEE_NO = "EMPLOYEE_NO";

	public String getEmployeeNoByCookies(Cookie[] cookies) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(EMPLOYEE_NO)) {
				return cookie.getValue();
			}
		}

		return null;
	}

	public Cookie getLoginCookie(String employeeNo) {
		Cookie cookie = makeEmployeeNoCookie(employeeNo, 60 * 60 * 24 * 365);
		return cookie;
	}

	public Cookie getLogoutCookie() {
		Cookie cookie = makeEmployeeNoCookie("", 0);
		return cookie;
	}

	public String findEmployeeNoCookie(Cookie[] cookies) {
		String employeeNo = null;

		for (Cookie cookie : cookies) {
			if (EMPLOYEE_NO.equals(cookie.getName())) {
				employeeNo = cookie.getValue();
			}
		}

		return employeeNo;
	}

	private Cookie makeEmployeeNoCookie(String employeeNo, int maxAge) {
		Cookie cookie = new Cookie(EMPLOYEE_NO, employeeNo);
		cookie.setMaxAge(maxAge);
		cookie.setPath("/");
		return cookie;
	}
}
