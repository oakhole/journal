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

package com.oakhole.voa.service;

import com.google.common.collect.Lists;
import com.oakhole.core.uitls.StringUtils;
import com.oakhole.voa.entity.WeixinMessage.responseMessage.ResImageMessage;
import com.oakhole.voa.entity.WeixinMessage.responseMessage.ResNewsMessage;
import com.oakhole.voa.entity.WeixinMessage.responseMessage.ResTextMessage;
import com.oakhole.voa.entity.WeixinMessage.responseMessage.ResVoiceMessage;
import com.oakhole.voa.utils.MessageType;
import com.oakhole.voa.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 消息业务处理，用于提取命令，执行对应操作
 *
 * @author oakhole
 * @since 1.0
 */
@Service
public class MessageService {

    private static final String event = "MESSAGE";

    @Autowired
    private static BusinessLogger logger;

    /**
     * 处理来自于用户推送的命令
     *
     * @param map
     * @return
     */
    public String processCommander(Map<String, String> map) {

        String toUserName = map.get("FromUserName");
        String fromUserName = map.get("ToUserName");
        String commander = getCommander(map).trim();

        logger.log(toUserName, event, map.get(MessageType.MESSAGE_TYPE), commander);

        String default_response = "So sorry, your openId is :" + toUserName +
                "\n【1】:文本消息\n【2】:图片消息\n【3】:语音消息\n【4】:视频消息\n【5】:音乐消息\n【6】:图文消息\n【0】:帮助";
        String default_rep = MessageUtils.textMessageToXml(new ResTextMessage(toUserName, fromUserName,
                default_response));

        if (StringUtils.isEmpty(commander) || commander.length() > 1 || commander.charAt(0) < '0' || commander.charAt(0)
                > '9') {
            return default_rep;
        }

        //多媒体文件为事先上传后获得的mediaId,需认证后使用
        switch (Integer.valueOf(commander)) {
            case 0:
                return MessageUtils.textMessageToXml(new ResTextMessage(toUserName, fromUserName,
                        "目前只开通了0，1，2，3，6等测试，部分功能尚无法使用，待认证后开通"));
            case 1:
                return MessageUtils.textMessageToXml(new
                        ResTextMessage(toUserName, fromUserName, "文本消息"));
            case 2:
                return MessageUtils.imageMessageToXml(new ResImageMessage(toUserName, fromUserName, ""));
            case 3:
                ResVoiceMessage.Voice voice = new ResVoiceMessage.Voice();
                voice.setMediaId("");
                return MessageUtils.voiceMessageToXml(new ResVoiceMessage(toUserName, fromUserName, voice));
            case 6:
                List<ResNewsMessage.Article> articles = Lists.newArrayList();
                articles.add(new ResNewsMessage.Article("图文消息", "该接口为常用接口，图片未添加，点击后可跳转到指定链接地址",
                        "", "http://www.baidu.com"));
                return MessageUtils.newsMessageToXml(new ResNewsMessage(toUserName, fromUserName, articles));
            default:
                return default_rep;
        }
    }

    /**
     * 提取commander信息
     * 1.基于Text文本消息
     * 2.基于语音识别信息
     * 3.其他暂不支持
     *
     * @return
     */
    private String getCommander(Map<String, String> map) {
        if (MessageType.TEXT_MESSAGE.equals(map.get(MessageType.MESSAGE_TYPE))) {
            return map.get("Content");
        }
        if (MessageType.VOICE_MESSAGE.equals(map.get(MessageType.MESSAGE_TYPE))) {
            return map.get("Recognition");
        }
        return "";
    }
}
