package com.hackjam.dictionary;

/**
 * Created by naver on 2017. 6. 18..
 */

public enum TemperatureDictionary {
    COLD(true,"차가운","차가운;찬;아이스;차갑게;시원한"),
    HOT(false,"따뜻한","따뜻한;따듯한;따수운;따순;뜨겁게;");

    private boolean isMeanIce;
    private String message;
    private String sameMeaningList;
    public static final String sameMeaningSeperator = ";";


    TemperatureDictionary(boolean isMeanIce,String message, String sameMeaningList){
        this.isMeanIce = isMeanIce;
        this.message = message;
        this.sameMeaningList = sameMeaningList;
    }

    public boolean isMeanIce(){
        return isMeanIce;
    }

    public String getMessage() {
        return message;
    }

    public String getSameMeaningList() {
        return sameMeaningList;
    }
}
