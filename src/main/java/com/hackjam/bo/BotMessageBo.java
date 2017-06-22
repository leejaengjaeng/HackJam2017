package com.hackjam.bo;

import com.hackjam.util.KoreanTextMiner;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.StickerMessage;
import com.linecorp.bot.model.message.TextMessage;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer.KoreanToken;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scala.collection.Seq;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by naver on 2017. 6. 18..
 */
@Service
public class BotMessageBo {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BotMessageBo.class);

    @Autowired
    private LineMessagingClient lineMessagingClient;

    @Autowired
    private KoreanTextMiner textMiner;

    BotMessageBo(){
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

    public String test(String input){

        CharSequence normalize = OpenKoreanTextProcessorJava.normalize(input);

        Seq<KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(input);
        List<KoreanToken> tokenList = scala.collection.JavaConversions.seqAsJavaList(tokens);

        StringBuffer testReturnString =new StringBuffer("Normalize 문자열 <br>"+normalize.toString()+"<br>토크나이징 시작<br>");
        for(KoreanToken token : tokenList){
            testReturnString.append("<br>"+token.toString());
        }

        return testReturnString.toString();
    }

    public TextMessage replyTextEvent(MessageEvent<TextMessageContent> textEvent){

        String inputMessage = textEvent.getMessage().getText();

        String testMiningedMessage = textMiner.miningTest(inputMessage);
        return new TextMessage(testMiningedMessage);
    }

    public StickerMessage replyUnhandledEvent(Event event){
        logger.info("unhandled Event\n"+event.getSource());
        //브라운이 물음표 하고있는 스티커
        String stickerPackageId = "2";
        String stickerId = "149";
        return new StickerMessage(stickerPackageId,stickerId);
    }
}
