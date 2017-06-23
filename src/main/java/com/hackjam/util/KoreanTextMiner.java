package com.hackjam.util;

import com.hackjam.constant.WordType;
import com.hackjam.dao.MenuDAO;
import com.hackjam.dictionary.BeverageDictionary;
import com.hackjam.dictionary.TemperatureDictionary;
import com.hackjam.model.Menu;
import com.hackjam.model.OrderDetail;
import org.openkoreantext.processor.KoreanTokenJava;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer.KoreanToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import scala.collection.Seq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by naver on 2017. 6. 18..
 */
@Component
public final class KoreanTextMiner {

    private static final Logger logger = LoggerFactory.getLogger(KoreanTextMiner.class);

    private static Map<String,Integer> MEANING_BEVERAGE_MENUS_MAP = new HashMap<>();
    private static Map<String,Boolean> MEANING_TEMPERATURE_MAP = new HashMap<>();
    private static Map<String,Integer> NUMBER_MAP = new HashMap<>();
    private static Map<String,String> OTHER_COMMANDS = new HashMap<>();

    @Autowired
    private MenuDAO menuDAO;

    KoreanTextMiner(){

        initBeverageMap();
        initTemperatureMap();

        initNumberMap();
        initOtherCommandMap();
        addCustomNouns();
    }

    private void addCustomNouns(){
        List<String> customNouns = new ArrayList<>();
        for(String menu : KoreanTextMiner.MEANING_BEVERAGE_MENUS_MAP.keySet()){
            customNouns.add(menu);
        }
        for(String temperature : KoreanTextMiner.MEANING_TEMPERATURE_MAP.keySet()){
            customNouns.add(temperature);
        }
        for(String koreanNumberPronunce : KoreanTextMiner.NUMBER_MAP.keySet()){
            customNouns.add(koreanNumberPronunce);
        }
        OpenKoreanTextProcessorJava.addNounsToDictionary(customNouns);
    }
    private void initBeverageMap(){

        for(BeverageDictionary beverage : BeverageDictionary.values()){
            if(!beverage.isOnSale())
            {
                continue;
            }
            String sameNames[] = beverage.getSameNames();

            for(String bevarageName : sameNames){
                MEANING_BEVERAGE_MENUS_MAP.put(bevarageName,beverage.getId());
            }
        }
    }
    private void initTemperatureMap(){
        String coldWords[] = TemperatureDictionary.COLD.getSameMeanings();
        String hotWords[] = TemperatureDictionary.HOT.getSameMeanings();
        for(String cold : coldWords){
            MEANING_TEMPERATURE_MAP.put(cold,TemperatureDictionary.COLD.isMeanHot());
        }
        for(String hot : hotWords){
            MEANING_TEMPERATURE_MAP.put(hot,TemperatureDictionary.HOT.isMeanHot());
        }
    }
    private void initNumberMap(){
        //spec out?
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
        NUMBER_MAP.put("하나",1);
        NUMBER_MAP.put("둘",2);
        NUMBER_MAP.put("셋",3);
        NUMBER_MAP.put("넷",4);

        NUMBER_MAP.put("스무",20);
        NUMBER_MAP.put("여덜",8);
        NUMBER_MAP.put("열여덜",18);
        NUMBER_MAP.put("스물여덜",28);
        NUMBER_MAP.put("서른여덜",38);
        NUMBER_MAP.put("마흔여덜",48);
    }
    private void initOtherCommandMap(){
        OTHER_COMMANDS.put("#ORDER_ANSWER_YES","ORDER_YES");
        OTHER_COMMANDS.put("#ORDER_ANSWER_NO","ORDER_NO");
    }

    public List<KoreanToken> getTokenListFromString(String input){

        logger.info("!!!!!!inputString\n"+input);
        CharSequence normalize = OpenKoreanTextProcessorJava.normalize(input);
        logger.info("!!!!!!inputString\n"+normalize);
        Seq<KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(normalize);
        List<KoreanToken> tokenList = scala.collection.JavaConversions.seqAsJavaList(tokens);

        return tokenList;
    }

    public WordType findWordType(KoreanToken token){

        String word = token.text();

        if(token.pos().toString().equals("Number")){
            return WordType.NUMBER;
        }
        if(NUMBER_MAP.containsKey(word)){
            return WordType.HANGUL_NUMBER;
        }
        if(MEANING_TEMPERATURE_MAP.containsKey(word)){
            return WordType.TEMPERATURE;
        }
        if(MEANING_BEVERAGE_MENUS_MAP.containsKey(word)){
            return WordType.BEVERAGE;
        }
        if(OTHER_COMMANDS.containsKey(word)){
            return WordType.OTHER_COMMAND;
        }
        return WordType.ORTHERS;
    }

    public String miningTest(String inputString){

        //CharSequence normalizedString = OpenKoreanTextProcessorJava.normalize(inputString);
        CharSequence normalizedString = inputString.replace(" ","");
        Seq<KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(normalizedString);
        List<KoreanTokenJava> javaTokens = OpenKoreanTextProcessorJava.tokensToJavaKoreanTokenList(tokens,false);

        StringBuffer testReturnString =new StringBuffer("Normalize 문자열 \n"+normalizedString.toString()+"\n토크나이징 시작\n");
        for(KoreanTokenJava javaToken : javaTokens){
            testReturnString.append("\n"+javaToken.getText() +" : "+javaToken.getPos());
        }

        return testReturnString.toString();
    }

    public boolean isMeanHot(String isMeanHot){
        return MEANING_TEMPERATURE_MAP.get(isMeanHot);
    }

    public int getMenuId(String menuString){
        return MEANING_BEVERAGE_MENUS_MAP.get(menuString);
    }

    public int getNumberFromKoreanNumber(String koreanPronounceNumber){
        return NUMBER_MAP.get(koreanPronounceNumber);
    }

    public OrderDetail getBeverageWithTemper(OrderDetail orderDetail){
        //TODO:뭔가 처리
        return orderDetail;
    }

}
