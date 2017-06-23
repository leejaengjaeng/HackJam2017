package com.hackjam.bo;

import com.hackjam.constant.BotOrderStep;
import com.hackjam.constant.WordType;
import com.hackjam.dictionary.BeverageDictionary;
import com.hackjam.dictionary.TemperatureDictionary;
import com.hackjam.model.Order;
import com.hackjam.model.OrderDetail;
import com.hackjam.util.KoreanTextMiner;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer.KoreanToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jaeyoung Lee on 2017-06-22.
 */
@Service
public class BotOrderProcessBo {

	private static final Logger logger = LoggerFactory.getLogger(BotOrderProcessBo.class);
	@Autowired
	private KoreanTextMiner textMiner;

	@Autowired
	private BotMessageUtil botMessageUtil;

	private Map<String, BotOrderStep> currentUsers = new HashMap<>();
	private Map<String, Order> onOrderUsers = new HashMap<>();

	public void addToCurrentUsers(String userId, BotOrderStep step){
		currentUsers.put(userId,step);
	}

	public Message replySuitableMessage(MessageEvent<TextMessageContent> event){
		String userId = event.getSource().getUserId();
		BotOrderStep currentStep = currentUsers.get(userId);
		return goSuitableNextStep(currentStep,event.getMessage().getText());
	}

	public Message goSuitableNextStep(BotOrderStep step, String userInput){
		if(step == null){
			step = BotOrderStep.HELLO;
		}

		switch (step){
			case HELLO:
			case READY:
			case ORDER_START:
			case ORDER_MORE:
			case CALL_USER:
			case ORDER_DONE:
			case CANT_ANSWER:
			default:
				logger.error("3");

				List<OrderDetail> orders = generateOrder(userInput);
				String a = "_\n";
				for(OrderDetail order : orders){
					a+= TemperatureDictionary.getKoreanWordHotIfTrue(order.isHot())+" "+ BeverageDictionary.getOriginalNameFromMenuId(order.getMenuId()) + " "+order.getCount() + "\n";
				}
				logger.error("#####\n###"+a);
				return botMessageUtil.replyTextEvent(a);
		}
	}

	private List<OrderDetail> generateOrder(String userInput) {
		logger.error("4\n"+userInput);

		List<KoreanToken> tokens = textMiner.getTokenListFromString(userInput);
		List<OrderDetail> completeOrders = new ArrayList<>();
		List<OrderDetail> notCompletedOrders = new ArrayList<>();

		OrderDetail currentMenu = new OrderDetail();

		WordType prevWordType = WordType.ORTHERS;
		for (KoreanToken token : tokens) {
			logger.error(token.text());

			WordType wordType = textMiner.findWordType(token);

			switch (wordType) {
				case BEVERAGE:
					int menuId = textMiner.getMenuId(token.text());
					currentMenu.setMenuId(menuId);
					break;
				case NUMBER:
					int quantity = textMiner.getNumberFromKoreanNumber(token.text());
					currentMenu.setCount(quantity);
					break;
				case HANGUL_NUMBER:
					int quantityHangul = textMiner.getNumberFromKoreanNumber(token.text());
					currentMenu.setCount(quantityHangul);
					break;
				case BEVERAGE_WITH_TEMPERATURE:
					currentMenu = textMiner.getBeverageWithTemper(currentMenu);
					break;
				case TEMPERATURE:
					boolean isHot = textMiner.isMeanHot(token.text());
					currentMenu.setHot(isHot);
					break;
				default:
					//TODO : 뭔가 처리
			}

			if (currentMenu.isDoneOrder()) {
				completeOrders.add(currentMenu.clone());
				currentMenu.setEmpty();
				logger.error(completeOrders.size()+" ");
			}
		}
		//TODO: 뭔가 처리
		return completeOrders;
	}

}
