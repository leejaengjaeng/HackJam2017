package com.hackjam.bo;

import com.hackjam.util.KoreanTextMiner;
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.message.StickerMessage;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.ConfirmTemplate;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer.KoreanToken;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Jaeyoung Lee on 2017. 6. 18..
 */
@Service
public class BotMessageUtil {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BotMessageUtil.class);

    @Autowired
    private KoreanTextMiner textMiner;

    public String test(String originalString){

        List<KoreanToken> tokenList = textMiner.getTokenListFromString(originalString);


        StringBuffer testReturnString =new StringBuffer("<br>토크나이징 시작<br>");
        for(KoreanToken token : tokenList){
            testReturnString.append("<br>"+token.text()+":"+token.pos().toString()+":"+token.productPrefix()+":"+token.productArity());
            if(token.pos().toString().equals("Number")){
                testReturnString.append("\nNumber\n");
            }
        }

        return testReturnString.toString();
    }


    public TextMessage replyJoinEvent(){
        String helloText = "주문하시겠어요?";
        logger.info("#######JoinEvent######");
        return new TextMessage(helloText);
    }

    public TextMessage replyText(Event event){
        return new TextMessage("dd");
    }


    public TextMessage replyTextEvent(String replyText){
        return new TextMessage(replyText);
    }

    public TemplateMessage replyTemplateMessage(String replyText){
        ConfirmTemplate confirmTemplate = new ConfirmTemplate(
                replyText,
                new PostbackAction("Yes","#ORDER_ANSWER_YES"),
                new PostbackAction("No","#ORDER_ANSWER_No")
        );

        return new TemplateMessage("라인에서 PC버전은 소홀한가 봅니다...",confirmTemplate);
    }

    public StickerMessage replyUnhandledEvent(Event event){
        logger.info("unhandled Event\n"+event.getSource());
        //브라운이 물음표 하고있는 스티커
        String stickerPackageId = "2";
        String stickerId = "149";
        return new StickerMessage(stickerPackageId,stickerId);
    }
}
