package com.hackjam.controller;

import com.hackjam.bo.BotMessageBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by naver on 2017. 6. 12..
 */
@RestController
public class TestController {

    @Autowired
    BotMessageBo botMessageBo;

    @RequestMapping("/test")
    public String test(@RequestParam String input){
        return botMessageBo.test(input);
    }
}
