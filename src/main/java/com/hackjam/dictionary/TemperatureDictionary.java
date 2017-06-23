package com.hackjam.dictionary;

/**
 * Created by Jaeyoung Lee on 2017. 6. 18..
 */

public enum TemperatureDictionary {
    COLD(false,"차가운","차가운;찬;아이스;차갑게;시원한"),
    HOT(true,"따뜻한","따뜻한;따듯한;따수운;따순;뜨겁게");

    private boolean isMeanHot;
    private String message;
    private String sameMeaningList;
    public static final String sameMeaningSeperator = ";";


    TemperatureDictionary(boolean isMeanIce,String message, String sameMeaningList){
        this.isMeanHot = isMeanIce;
        this.message = message;
        this.sameMeaningList = sameMeaningList;
    }

    public boolean isMeanHot(){
        return isMeanHot;
    }

    public String getMessage() {
        return message;
    }

    public String[] getSameMeanings() {
        return sameMeaningList.split(sameMeaningSeperator);
    }


    public static String getKoreanWordHotIfTrue(boolean input){
        if(input == COLD.isMeanHot){
            return COLD.message;
        }
        return HOT.message;
    }
}
