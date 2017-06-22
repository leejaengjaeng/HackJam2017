package com.hackjam.model;

/**
 * Created by naver on 2017. 6. 18..
 */
public class OrderedBeverage {
    private String beverageId;
    private String beverageOriginalName;
    private int pricePerOne;
    private boolean isIce;
    private int quantity;

    public int getPricePerOne() {
        return pricePerOne;
    }

    public void setPricePerOne(int pricePerOne) {
        this.pricePerOne = pricePerOne;
    }

    public String getBeverageId() {
        return beverageId;
    }

    public void setBeverageId(String beverageId) {
        this.beverageId = beverageId;
    }

    public String getBeverageOriginalName() {
        return beverageOriginalName;
    }

    public void setBeverageOriginalName(String beverageOriginalName) {
        this.beverageOriginalName = beverageOriginalName;
    }

    public boolean isIce() {
        return isIce;
    }

    public void setIce(boolean ice) {
        isIce = ice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
