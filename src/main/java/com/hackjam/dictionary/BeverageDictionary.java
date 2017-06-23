package com.hackjam.dictionary;
/**
 * Created by Jaeyoung Lee on 2017. 6. 18..
 */

//TODO: DB에 넣고 지우기
public enum BeverageDictionary {
    없는메뉴(0,"없는메뉴",0,true,"ajdlsajdlsakdjlasdkla",true),
    에스프레소(1,"에스프레소",700,true,"에스프레소;ㅇㅅㅍㄹㅅ",true),
    아메리카노(2,"아메리카노",700,true,"아메리카노;아아;따아;아메;ㅇㅁ;ㅇㅁㄹㅋㄴ;ㄸㅇ",false),
    카페라떼(3,"카페라떼",700,true,"카페라떼;카페라테;라떼;라테;ㅋㅍㄹㅌ;ㄹㅌ;ㄹㄸ",true),
    카페모카(4,"카페모카",700,true,"카페모카;모카;ㅋㅍㅁㅋ;ㅁㅋ",true),
    카라멜마끼아또(5,"카라멜 마끼아또",700,true,"카라멜마끼아또;마끼아또;마키야또;마끼아토;마키야토;ㅁㄲㅇㄸ;ㅁㅋㅇㅌ;ㅋㄹㅁㅁㅋㅇㅌ;ㅋㄹㅁㅁㄲㅇㄸ;ㅋㄹㅁㅁㄲㅇㅌ",true),
    모카얼티밋프라페(6,"모카 얼티밋 프라페",700,true,"모카얼티밋프라페;모카프라페;프라페;얼티밋프라페;ㅍㄹㅍ;ㅇㅌㅁㅍㄹㅍ",false),

    홍초(7,"홍초",700,true,"홍초;ㅎㅊ;식초",false),
    사과즙(8,"사과즙",700,true,"사과즙;ㅅㄱㅈ",false),
    유자차(9,"유자차",700,true,"유자차;ㅇㅈㅊ",true),
    청포도홍차(10,"청포도 홍차",700,true,"청포도홍차;홍차;청포도;ㅊㅍㄷㅎㅊ",false),
    루이보스허브티(11,"루이보스 허브티",700,true,"루이보스허브티;루이보스허브차;루이보스;ㄹㅇㅂㅅ;루이;보스",true),
    페퍼민트허브차(12,"페퍼민트 허브차",700,true,"페퍼민트허브차;페퍼민트허브티;페퍼민트;ㅍㅍㅁㅌ;패퍼민트",true),

    생오렌지주스(13,"생 오렌지 주스",2000,true,"생오렌지주스;생과일주스;오렌지주스;오렌지;주스;오랜지;ㅇㄹㅈㅈㅅ;ㅇㄹㅉㅅ;ㅇㄹㅈ",false),
    로얄밀크버블티(14,"로얄 밀크 버블티",1800,true,"로얄밀크버블티;버블티;밀크티;ㅃㅌ;ㅂㅂㅌ;ㅁㅋㅌ",false),
    허니자몽(15,"허니자몽",1800,true,"허니자몽;ㅎㄴㅈㅁ;자몽;ㅈㅁ;꿀자몽",true),
    미숫가루(16,"미숫가루",1200,true,"미숫가루;ㅁㅅㄱㄹ;미수가루",false),
    복숭아아이스티(17,"복숭아 아이스티",1000,true,"복숭아아이스티;복숭아;아이스티;ㅂㅅㅇ;ㅇㅇㅅㅌ;ㅂㅅㅇㅇㅇㅅㅌ",false),

    로즈블러썸(18,"로즈 블러썸",1800,true,"로즈블러썸;ㄹㅈㅂㄹㅆ;로즈블러섬;로즈블라썸;로즈블라섬;블라섬;블러섬;블라썸;블러썸;ㄹㅈㅂㄹㅅ",false),
    벚꽃스무디(19,"벚꽃 스무디",1500,true,"벚꽃스무디;스무디;ㅂㄲㅅㅁㄷ;ㅅㅁㄷ;ㅂㄲ",false);

    private int id;
    private String originalName;
    private int price;
    private boolean onSale;
    private String sameNamesWithSeperator;
    private boolean defaultIsHot;
    private static final String SAME_NAME_SEPERATOR = ";";


    BeverageDictionary(int id,String originalName,int price,boolean onSale, String sameNamesWithSeperator, boolean defaultIsHot){
        this.id = id;
        this.originalName = originalName;
        this.price = price;
        this.onSale = onSale;
        this.sameNamesWithSeperator = sameNamesWithSeperator;
        this.defaultIsHot = defaultIsHot;
    }

    public int getId() {
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

    public String[] getSameNames(){
        return sameNamesWithSeperator.split(SAME_NAME_SEPERATOR);
    }

    public static String getOriginalNameFromMenuId(int menuId){
        for(BeverageDictionary beverage : BeverageDictionary.values()){
            if(beverage.id == menuId){
                return beverage.originalName;
            }
        }
        return "";
    }

    public static boolean isDefaultValueHot(int menuId){
        for(BeverageDictionary beverage : BeverageDictionary.values()){
            if(beverage.id == menuId){
                return beverage.defaultIsHot;
            }
        }
        return false;
    }
}
