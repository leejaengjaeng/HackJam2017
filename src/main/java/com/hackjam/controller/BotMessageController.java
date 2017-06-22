package com.hackjam.controller;

import com.hackjam.bo.BotMessageBo;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by naver on 2017. 6. 18..
 */
@LineMessageHandler
public class BotMessageController {

    private static final Logger logger = LoggerFactory.getLogger(BotMessageController.class);

    @Autowired
    BotMessageBo botMessageBo;

    @EventMapping
    public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        logger.info("TextMessage Event");
        return botMessageBo.replyTextEvent(event);
    }
    @EventMapping
    public Message defaultMessageEvent(Event event){
        logger.info("default event : "+event);
        return botMessageBo.replyUnhandledEvent(event);
    }
}
