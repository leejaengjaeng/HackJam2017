package com.hackjam.model;

/**
 * Created by naver on 2017. 6. 18..
 */
public class Beverage {

    private String id;
    private String originalName;
    private int price;
    private boolean onSale;
    private String sameNamesWithSeparator;
    public static final String sameNameSeparator = ";";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }

    public String getSameNamesWithSeparator() {
        return sameNamesWithSeparator;
    }

    public void setSameNamesWithSeparator(String sameNamesWithSeparator) {
        this.sameNamesWithSeparator = sameNamesWithSeparator;
    }
}
