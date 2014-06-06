/*
 * Copyright (c) 2013-2014. Powered by http://oakhole.com .
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.oakhole.voa.web;

import com.oakhole.core.uitls.XMLMapper;
import com.oakhole.voa.entity.Authentication;
import com.oakhole.voa.service.AuthService;
import com.oakhole.voa.service.EventService;
import com.oakhole.voa.service.MessageService;
import com.oakhole.voa.utils.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * 根据请求信息的类型判断属于普通消息、事件推送和语音消息,然后根据设定进行回复或跳转页面
 *
 * @author oakhole
 * @since 1.0
 */
@RestController
@RequestMapping("/")
public class WeixinController {

    private static Logger logger = LoggerFactory.getLogger(WeixinController.class);

    @Autowired
    private AuthService authService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private EventService eventService;

    /**
     * 进行开发者认证
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String auth(@Valid Authentication authentication) {
        if (this.authService.validate(authentication)) {
            logger.info("微信接口接入成功");
            return authentication.getEchostr();
        }
        logger.error("请求信息: Signature:{},nonce:{},timeStamp:{},echostr:{}",
                authentication.getSignature(),
                authentication.getNonce(), authentication.getTimestamp(),
                authentication.getEchostr());
        logger.error("微信接口接入失败");
        return "error";
    }

    /**
     * 对请求消息和事件进行处理
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String process(ServletRequest request) {
        Map<String, String> map = null;
        try {
            map = XMLMapper.parseXml(request.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("map:{}", map.toString());
        if (!map.isEmpty()) {
            if (MessageType.EVENT_MESSAGE.equals(map.get(MessageType.MESSAGE_TYPE))) {
                return this.eventService.processEvent(map);
            }
            return this.messageService.processCommander(map);
        }
        return "";
    }
}
