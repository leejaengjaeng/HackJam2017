package com.hackjam.constant;

/**
 * @author jun-ho.lee on 2017-06-21.
 */
public enum MenuType {
	COFFEE("coffee"),
	JUICE_TEA("juice_tea"),
	PREMIUM("premium"),
	SEASON("season");

	private String name;

	MenuType( String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
