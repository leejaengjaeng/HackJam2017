package com.hackjam.bo;

import com.hackjam.constant.BotOrderStep;
import com.hackjam.constant.WordType;
import com.hackjam.dictionary.BeverageDictionary;
import com.hackjam.model.Order;
import com.hackjam.model.OrderDetail;
import com.hackjam.util.KoreanTextMiner;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer.KoreanToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	private static Map<String, Order> onOrder = new HashMap<>();
	private static Map<String, Integer> abusingScore = new HashMap<>();

	@Autowired
	private BotMessageUtil botMessageUtil;

	private Map<String, BotOrderStep> currentUsers = new HashMap<>();
	private Map<String, Order> onOrderUsers = new HashMap<>();

	public void addToCurrentUsers(String userId, BotOrderStep step){
		currentUsers.put(userId,step);
	}

	public Message replySuitableMessage(MessageEvent<TextMessageContent> event){
		String userId = event.getSource().getUserId();
		if(abusingScore.get(userId)==null){
			abusingScore.put(userId,0);
		}

		logger.info("#####userId : " +userId);
		BotOrderStep currentStep = currentUsers.get(userId);

		return goSuitableNextStep(currentStep,event.getMessage().getText(),userId);
	}

	private Message goSuitableNextStep(BotOrderStep step, String userInput,String userId){

		if(step == null){
			step = BotOrderStep.HELLO;
		}

		return generateOrder(userInput, userId);
	}

	private Message generateOrder(String userInput, String userId) {

		List<KoreanToken> tokens = textMiner.getTokenListFromString(userInput);
		List<OrderDetail> completeOrders = new ArrayList<>();
		OrderDetail currentMenu = new OrderDetail();

		for (KoreanToken token : tokens) {
			String tokenText = token.text();
			WordType wordType = textMiner.findWordType(token);
			switch (wordType) {
				case BEVERAGE:
					logger.info("#음료 영역 : "+tokenText);
					int menuId = textMiner.getMenuId(tokenText);
					boolean isDefaultTemperatureHot = BeverageDictionary.isDefaultValueHot(menuId);
					if(currentMenu.getMenuId()==0){
						currentMenu.setMenuId(menuId);
						currentMenu.setHot(isDefaultTemperatureHot);
					}
					else{
						completeOrders.add(doneOneOrder(currentMenu));
						currentMenu.setEmpty();
						currentMenu.setMenuId(menuId);
						currentMenu.setHot(isDefaultTemperatureHot);
					}
					//TODO: 여기에 예외 온도 처리
					if(tokenText.equals("따아")){
						currentMenu.setHot(true);
						currentMenu.setTemperatureSelect(true);
					}

					break;
				case NUMBER:
				case HANGUL_NUMBER:
					logger.info("#숫자 영역 : "+tokenText);
					int quantity = wordType==WordType.NUMBER ? Integer.parseInt(tokenText) : textMiner.getNumberFromKoreanNumber(tokenText);

					if(currentMenu.getCount()==0){
						currentMenu.setCount(quantity);
					}
					else {
						completeOrders.add(doneOneOrder(currentMenu));
						currentMenu.setEmpty();
						currentMenu.setCount(quantity);
					}
					break;
				case TEMPERATURE:
					logger.info("#온도 영역 : "+tokenText);
					boolean isHot = textMiner.isMeanHot(tokenText);
					if(!currentMenu.isTemperatureSelect()){
						currentMenu.setHot(isHot);
						currentMenu.setTemperatureSelect(true);
					}
					else if(currentMenu.getMenuId()!=0){
						completeOrders.add(doneOneOrder(currentMenu));
						currentMenu.setEmpty();
						currentMenu.setHot(isHot);
						currentMenu.setTemperatureSelect(true);
					}
					else{
						logger.error("####온도만 두번 연속 나옴\n기존 : "+currentMenu.toString()+"\n입력된 온도 :"+ tokenText);
						continue;
					}
					break;
				case OTHER_COMMAND:
					logger.info("#응답");
				default:
					logger.info("#default 영역 : " + tokenText);
					//TODO : 뭔가 처리
			}
		}
		if(!currentMenu.isEmpty()){
			completeOrders.add(doneOneOrder(currentMenu));
		}

		if(completeOrders.size()==0){
			int usersAbusingScore = abusingScore.get(userId);
			abusingScore.put(userId,usersAbusingScore+1);
			if(usersAbusingScore>5){
				return botMessageUtil.replyTextEvent("적당히 해..");
			}
			if(usersAbusingScore>1){
				return botMessageUtil.replyTextEvent("주문만 해주세요.");
			}
			return botMessageUtil.replyTextEvent("주문 하시겠어요?");
		}
		else{
			abusingScore.put(userId,0);

			//주문 확인
			Order newOrder = new Order();
			newOrder.setStatus(0);
			newOrder.setOrderDetails(completeOrders);
			onOrder.put(userId,newOrder);
			return makeTextReplyMessage(completeOrders);
		}
	}

	private Message makeTextReplyMessage(List<OrderDetail> orders){
		String infoMessage = "주문 내역 확인해 드리겠습니다.\n";
		String confirmText = "";
		int ordersCount = orders.size();
		for(int i=0; i< ordersCount-1 ;i++){
			confirmText+=orders.toString()+",\n";
		}
		confirmText += orders.get(ordersCount-1).toString()+"맞나요?";

		if(confirmText.equals("")){
			//여기 안들어오는듯
			confirmText = "주문만 해주세요~";
			return botMessageUtil.replyTextEvent(confirmText);
		}
		else{
			return botMessageUtil.replyTemplateMessage(infoMessage+confirmText);
		}
	}

	private OrderDetail doneOneOrder(OrderDetail currentOrder){
		logger.info("####doneOneOrder Before\n"+currentOrder);
		if(currentOrder.getCount() == 0){
			currentOrder.setCount(1);
		}
		if(!currentOrder.isTemperatureSelect()){
			boolean isDefaultHot = currentOrder.getMenu().getDefaultTemperature() == "hot" ? true:false;
			currentOrder.setHot(isDefaultHot);
		}
		logger.info("####doneOneOrder After\n"+currentOrder);
		return currentOrder.clone();
	}


	public Message replyPostBackEvent(PostbackEvent event){

		String userId = event.getSource().getUserId();
		String reply = event.getPostbackContent().getData();

		Order currentOrder = onOrder.get(userId);
		//주문 진행중
		if(currentOrder==null){
			return botMessageUtil.replyTextEvent("종료된 주문이에요.\n새로 주문 하시겠어요?");
		}

		if(reply.equals("#ORDER_ANSWER_YES")){

			if(currentOrder.getStatus()==1)
			{

				return botMessageUtil.replyTextEvent("음료를 만드는 중 입니다...\n조금 더 기다려주세요 :)");
			}
			else{
				currentOrder.setStatus(1);
				return botMessageUtil.replyTextEvent("주문해 주셔서 감사합니다.\n음료가 나오면 알려드릴게요");
			}
		}
		else{
			if(onOrder.get(userId).getStatus()==1){
				return botMessageUtil.replyTextEvent("취소하기엔 늦었어요");
			}
			else{
				onOrder.remove(userId);
				return botMessageUtil.replyTextEvent("흥! 새로 주문 하시던지!\n\n주문 하시겠어요? ^^ ");
			}
		}
	}
}
