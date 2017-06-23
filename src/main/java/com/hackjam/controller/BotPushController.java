package com.hackjam.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

/**
 * Created by Jaeyoung Lee on 2017. 6. 18..
 */
@RestController
public class BotPushController {

	private static final Logger logger = LoggerFactory.getLogger(BotPushController.class);

	@RequestMapping("/botPush")
	public String pushDoneMessage(@RequestParam String userId) throws Exception {
		logger.info("@@@UserID : " + userId);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap();
		body.add("to", userId);
		Message pushMessages[] = {new TextMessage("음료가 완성되었습니다.\n받으러 와주세요~")};
		body.add("messages", pushMessages);

		String t = "QTCRhvV69pDaO89Va0KmKFQGQbldX42mbzbXu1f+NJIGQo+qVZcFe/SuUn7s529/GeQTJYgp3N88TFLoFpPmXRTvKKkhhf/2jQVXlYchb6biE5qamkwDYQIz9364yO1UBXSj2XltadtlR3in6e9DDAdB04t89/1O/w1cDnyilFU=";
		HttpPost httpPost = new HttpPost("https://api.line.me/v2/bot/message/push");
		httpPost.setHeader("Content-Type", "application/json");
		httpPost.setHeader("Authorization", "Bearer " + t);

		ObjectMapper objectMapper = new ObjectMapper();
		String bodyString = objectMapper.writeValueAsString(body);
		StringEntity stringEntity = new StringEntity(bodyString);
		httpPost.setEntity(stringEntity);

		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			return EntityUtils.toString(httpResponse.getEntity());
		} catch (IOException ioe) {
			logger.error("{}", ioe);
			return ioe.getMessage();
		}
	}

	@RequestMapping("/botPush2")
	public void pushDoneMessage2(@RequestParam String userId) {
		String t = "QTCRhvV69pDaO89Va0KmKFQGQbldX42mbzbXu1f+NJIGQo+qVZcFe/SuUn7s529/GeQTJYgp3N88TFLoFpPmXRTvKKkhhf/2jQVXlYchb6biE5qamkwDYQIz9364yO1UBXSj2XltadtlR3in6e9DDAdB04t89/1O/w1cDnyilFU=";
		logger.info("@@@UserID : " + userId);

		MultiValueMap<String, String> header = new LinkedMultiValueMap();
		header.add("Content-Type", "application/json");
		header.add("Authorization", "Bearer " + t);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap();
		body.add("to", userId);
		Message pushMessages[] = {new TextMessage("음료가 완성되었습니다.\n받으러 와주세요~")};
		body.add("messages", pushMessages);

		RestTemplate callPush = new RestTemplate();
		callPush.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, header);

		callPush.postForObject("http://localhost:8080/kkkk", request, String.class);
	}

	@RequestMapping("/kkkk")
	public void dasdasd(HttpServletRequest req) {

		String a = "";

	}
}
