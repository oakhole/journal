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

package com.oakhole.voa.utils;

import com.oakhole.core.uitls.StringUtils;
import com.oakhole.voa.entity.WeixinMessage.WeixinMessage;
import com.oakhole.voa.entity.WeixinMessage.requestMessage.ReqImageMessage;
import com.oakhole.voa.entity.WeixinMessage.requestMessage.ReqTextMessage;
import com.oakhole.voa.entity.WeixinMessage.requestMessage.ReqVoiceMessage;

import java.util.Map;

/**
 * 工厂类，生成各种类型的消息或事件
 *
 * @author oakhole
 * @since 1.0
 */
public class WeixinMessageProducer {

    /**
     * 根据解析xml后生成的map,创建message的实例
     *
     * @param map
     * @return
     */
    public WeixinMessage buildMessage(Map map) {

        String messageType = (String)map.get(MessageType.MESSAGE_TYPE);
        if (!StringUtils.isEmpty(messageType)) {
            //1.判断消息类型
            if (MessageType.TEXT_MESSAGE.equals(messageType)) {
                return new ReqTextMessage();
            }else if(MessageType.IMAGE_MESSAGE.equals(messageType)){
                return new ReqImageMessage();
            }else if (MessageType.VOICE_MESSAGE.equals(messageType)) {
                return new ReqVoiceMessage();
            }
            //todo:暂时放这儿
        }
        return null;
    }
}
