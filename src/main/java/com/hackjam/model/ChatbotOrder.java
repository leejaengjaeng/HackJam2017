package com.hackjam.model;

import java.util.Date;
import java.util.List;

/**
 * Created by naver on 2017. 6. 18..
 */
public class ChatbotOrder extends Order{

	private String orderString;

	public String getOrderString() {
		return orderString;
	}

	public void setOrderString(String orderString) {
		this.orderString = orderString;
	}
}
