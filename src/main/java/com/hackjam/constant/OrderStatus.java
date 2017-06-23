package com.hackjam.constant;

/**
 * @author jun-ho.lee on 2017-06-23.
 */
public enum OrderStatus {
	SEND(0),
	RECEIVE(1),
	DONE(2);

	private int status;

	OrderStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}
