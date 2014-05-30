/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.oakhole.core.jms;

import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;
import java.util.Map;

/**
 * JMS用户变更消息生产者.
 * 
 * 使用jmsTemplate将用户变更消息分别发送到queue与topic.
 * 
 * @author calvin
 */

public class NotifyMessageProducer {

	private JmsTemplate jmsTemplate;
	private Destination notifyQueue;
	private Destination notifyTopic;

    /**
     * 更改原先默认User实例，由上层构造数据传入
     * @param dataMap
     */
	public void sendQueue(final Map<Object,Object> dataMap) {
		sendMessage(dataMap, notifyQueue);
	}

	public void sendTopic(final Map<Object,Object> dataMap) {
		sendMessage(dataMap, notifyTopic);
	}

	/**
	 * 使用jmsTemplate最简便的封装convertAndSend()发送Map类型的消息.
	 */
	private void sendMessage(Map<Object,Object> dataMap, Destination destination) {
		jmsTemplate.convertAndSend(destination, dataMap);
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void setNotifyQueue(Destination notifyQueue) {
		this.notifyQueue = notifyQueue;
	}

	public void setNotifyTopic(Destination nodifyTopic) {
		this.notifyTopic = nodifyTopic;
	}
}
