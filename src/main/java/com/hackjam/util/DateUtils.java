package com.hackjam.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jun-ho.lee on 2017-06-22.
 */
public class DateUtils {
	private static final SimpleDateFormat YMDT_FORMAT = new SimpleDateFormat("yy-MM-dd HH:mm");

	public static String formatYmdt(Date date) {
		if (date == null) {
			return null;
		} else {
			return YMDT_FORMAT.format(date);
		}
	}
}
