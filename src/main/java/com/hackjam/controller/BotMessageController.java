package com.hackjam.controller;

import com.hackjam.bo.BotMessageUtil;
import com.hackjam.bo.BotOrderProcessBo;
import com.hackjam.constant.BotOrderStep;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.JoinEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Jaeyoung Lee on 2017. 6. 18..
 */
@LineMessageHandler
public class BotMessageController {

    private static final Logger logger = LoggerFactory.getLogger(BotMessageController.class);

    @Autowired
    BotMessageUtil botMessageUtil;

    @Autowired
    BotOrderProcessBo botOrderProcessBo;

    @EventMapping
    public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        logger.info("TextMessage Event\n"+event.getMessage().getText());

        return botOrderProcessBo.replySuitableMessage(event);
    }

    @EventMapping
    public Message hadlePostBackEvent(PostbackEvent event){
        logger.info("Postback Event\n");
        return botOrderProcessBo.replyPostBackEvent(event);
    }

    @EventMapping
    public Message handleJoinEvnet(JoinEvent event){
        logger.debug("#####여기 들어오긴하냐!!!######");
        String userId = event.getSource().getUserId();
        botOrderProcessBo.addToCurrentUsers(userId, BotOrderStep.HELLO);
        return botMessageUtil.replyJoinEvent();
    }

    @EventMapping
    public Message defaultMessageEvent(Event event){
        String senderId = event.getSource().getSenderId();
        logger.info("default event : "+event);
        return botMessageUtil.replyUnhandledEvent(event);
    }
}
