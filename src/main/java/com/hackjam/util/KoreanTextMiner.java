package com.hackjam.util;

import com.hackjam.dictionary.BeverageDictionary;
import com.hackjam.dictionary.TemperatureDictionary;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer;
import org.openkoreantext.processor.KoreanTokenJava;
import org.springframework.stereotype.Component;
import scala.collection.Seq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by naver on 2017. 6. 18..
 */
@Component
public final class KoreanTextMiner {

    public static Map<String,String> MEANING_BEVERAGE_MENUS_MAP = new HashMap<>();
    public static Map<String,Boolean> MEANING_TEMPERATURE_MAP = new HashMap<>();
    public static Map<String,Integer> NUMBER_MAP = new HashMap<>();

    KoreanTextMiner(){
        initKoreanTextMiner();
    }
    private void initKoreanTextMiner(){
        //여기에서 초기화 말고 사전에 다 넣어버릴까
        initBeverageMap();
        initTemperatureMap();
        initNumberMap();
    }
    private void initBeverageMap(){
        //TODO:DB에서 꺼내서 넣기
        for(BeverageDictionary beverage : BeverageDictionary.values()){
            if(!beverage.isOnSale())
            {
                continue;
            }
            String sameNames[] = beverage.getSameNamesWithSeperator().split(BeverageDictionary.sameNameSeperator);

            for(String bevarageName : sameNames){
                MEANING_BEVERAGE_MENUS_MAP.put(bevarageName,beverage.getId());
            }
        }
    }
    private void initTemperatureMap(){
        String coldWords[] = TemperatureDictionary.COLD.getSameMeaningList().split(TemperatureDictionary.sameMeaningSeperator);
        String hotWords[] = TemperatureDictionary.HOT.getSameMeaningList().split(TemperatureDictionary.sameMeaningSeperator);
        for(String cold : coldWords){
            MEANING_TEMPERATURE_MAP.put(cold,TemperatureDictionary.COLD.isMeanIce());
        }
        for(String hot : hotWords){
            MEANING_TEMPERATURE_MAP.put(hot,TemperatureDictionary.HOT.isMeanIce());
        }
    }
    private void initNumberMap(){
        String koreanUnitsDigitPronunces[] = {
                "",
                "한",
                "두",
                "세",
                "네",
                "다섯",
                "여섯",
                "일곱",
                "여덟",
                "아홉",
        };
        String koreanTenthDigitPronunces[] = {
                "",
                "열",
                "스물",
                "서른",
                "마흔",
        };

        for(int num=1;num<50;num++){
            String tenthDigit = koreanTenthDigitPronunces[num/10];
            String unitDigit = koreanUnitsDigitPronunces[num%10];
            NUMBER_MAP.put(tenthDigit+unitDigit,num);
        }

        //예외처리
        NUMBER_MAP.remove("스물");
        NUMBER_MAP.put("스무",20);
        NUMBER_MAP.put("여덜",8);
        NUMBER_MAP.put("열여덜",18);
        NUMBER_MAP.put("스물여덜",28);
        NUMBER_MAP.put("서른여덜",38);
        NUMBER_MAP.put("마흔여덜",48);
    }

    public String miningTest(String inputString){

        CharSequence normalizedString = OpenKoreanTextProcessorJava.normalize(inputString);
        Seq<KoreanTokenizer.KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(normalizedString);
        List<KoreanTokenJava> javaTokens = OpenKoreanTextProcessorJava.tokensToJavaKoreanTokenList(tokens,false);

        StringBuffer testReturnString =new StringBuffer("Normalize 문자열 \n"+normalizedString.toString()+"\n토크나이징 시작\n");
        for(KoreanTokenJava javaToken : javaTokens){
            testReturnString.append("\n"+javaToken.getText() +" : "+javaToken.getPos());
        }

        return testReturnString.toString();
    }







}
