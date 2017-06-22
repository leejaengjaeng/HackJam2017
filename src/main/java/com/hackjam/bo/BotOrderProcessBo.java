package com.hackjam.bo;

import com.hackjam.constant.BotOrderStep;
import com.hackjam.model.Order;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jaeyoung Lee on 2017-06-22.
 */
@Service
public class BotOrderProcessBo {

	private Map<String, BotOrderStep> currentUsers = new HashMap<>();
	private Map<String, Order> onOrderUsers = new HashMap<>();

	public void addToCurrentUsers(String userId, BotOrderStep step){
		currentUsers.put(userId,step);
	}

	public void addToOnOrder(String userId, Order order){
		onOrderUsers.put(userId,order);
	}

	public BotOrderStep getCurrentUsersStep(String userId){
		return currentUsers.get(userId);
	}

}
