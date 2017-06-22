package com.hackjam.dictionary;

/**
 * Created by naver on 2017. 6. 18..
 */

//TODO: DB에 넣고 지우기
public enum BeverageDictionary {
    에스프레소("b001","에스프레소",700,true,"에스프레소;ㅇㅅㅍㄹㅅ"),
    아메리카노("b002","아메리카노",700,true,"아메리카노;아아;따아;아메;ㅇㅁ;ㅇㅇ;ㅇㅁㄹㅋㄴ;ㄸㅇ"),
    카페라떼("b003","카페라떼",700,true,"카페라떼;카페라테;라떼;라테;ㅋㅍㄹㅌ;ㄹㅌ;ㄹㄸ"),
    카페모카("b004","카페모카",700,true,"카페모카;모카;ㅋㅍㅁㅋ;ㅁㅋ"),
    카라멜마끼아또("b005","카라멜 마끼아또",700,true,"카라멜마끼아또;마끼아또;마키야또;마끼아토;마키야토;ㅁㄲㅇㄸ;ㅁㅋㅇㅌ;ㅋㄹㅁㅁㅋㅇㅌ;ㅋㄹㅁㅁㄲㅇㄸ;ㅋㄹㅁㅁㄲㅇㅌ"),
    모카얼티밋프라페("b006","모카 얼티밋 프라페",700,true,"모카얼티밋프라페;모카프라페;얼티밋;프라페;얼티밋프라페;ㅍㄹㅍ;ㅇㅌㅁㅍㄹㅍ"),

    홍초("b007","홍초",700,true,"홍초;ㅎㅊ;식초"),
    사과즙("b008","사과즙",700,true,"사과즙;ㅅㄱㅈ"),
    유자차("b009","유자차",700,true,"유자차;ㅇㅈㅊ"),
    청포도홍차("b010","청포도 홍차",700,true,"청포도홍차;홍차;청포도;ㅊㅍㄷㅎㅊ"),
    루이보스허브티("b011","루이보스 허브티",700,true,"루이보스허브티;루이보스허브차;루이보스;ㄹㅇㅂㅅ;루이;보스"),
    페퍼민트허브차("b012","페퍼민트 허브차",700,true,"페퍼민트허브차;페퍼민트허브티;페퍼민트;ㅍㅍㅁㅌ;패퍼민트"),

    생오렌지주스("b013","생 오렌지 주스",2000,true,"생오렌지주스;생과일주스;오렌지주스;오렌지;주스;오랜지;ㅇㄹㅈㅈㅅ;ㅇㄹㅉㅅ;ㅇㄹㅈ"),
    로얄밀크버블티("b014","로얄 밀크 버블티",1800,true,"로얄밀크버블티;버블티;밀크티;ㅃㅌ;ㅂㅂㅌ;ㅁㅋㅌ"),
    허니자몽("b015","허니자몽",1800,true,"허니자몽;ㅎㄴㅈㅁ;자몽;ㅈㅁ;꿀자몽"),
    미숫가루("b016","미숫가루",1200,true,"미숫가루;ㅁㅅㄱㄹ;미수가루"),
    복숭아아이스티("b017","복숭아 아이스티",1000,true,"복숭아아이스티;복숭아;아이스티;ㅂㅅㅇ;ㅇㅇㅅㅌ;ㅂㅅㅇㅇㅇㅅㅌ"),

    로즈블러썸("b018","로즈 블러썸",1800,true,"로즈블러썸;ㄹㅈㅂㄹㅆ;로즈블러섬;로즈블라썸;로즈블라섬;ㄹㅈㅂㄹㅅ"),
    벚꽃스무디("b019","벚꽃 스무디",1500,true,"벚꽃스무디;벚꽃;스무디;ㅂㄲㅅㅁㄷ;ㅅㅁㄷ;ㅂㄲ");

    private String id;
    private String originalName;
    private int price;
    private boolean onSale;
    private String sameNamesWithSeperator;
    public static final String sameNameSeperator = ";";


    BeverageDictionary(String id,String originalName,int price,boolean onSale, String sameNamesWithSeperator){
        this.id = id;
        this.originalName = originalName;
        this.price = price;
        this.onSale = onSale;
        this.sameNamesWithSeperator = sameNamesWithSeperator;
    }

    public String getId() {
        return id;
    }

    public String getOriginalName() {
        return originalName;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public String getSameNamesWithSeperator() {
        return sameNamesWithSeperator;
    }
}
