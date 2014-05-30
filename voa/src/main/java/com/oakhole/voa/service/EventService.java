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

import com.oakhole.core.uitls.StringUtils;
import com.oakhole.voa.utils.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * @author oakhole
 * @since 1.0
 */
@Service
public class EventService {

    private static final String event = "EVENT";

    @Autowired
    private static BusinessLogger logger;

    /**
     * 根据接口提供的各类事件作出处理
     *
     * @param map
     * @return 返回封装后xml字符串，默认为空
     */
    public String processEvent(Map<String, String> map) {

        String fromUserName = map.get("fromUserName");  //用户对应公众号的openId

        String event = map.get(EventType.EVENT_TYPE);
        //判断event类型非空
        Assert.notNull(event, "Event can not be null");

        //关注事件
        if (EventType.SUBSCRIBE_EVENT.equals(event)) {
            //判断是否来自于带参数的二维码,未关注扫描二维码事件有EventKey字段,qrscene_为前缀，后面为二维码的参数值
            String eventKey = map.get("EventKey");
            if (StringUtils.isEmpty(eventKey)) {
                //Ticket 二维码的ticket，可用来换取二维码图片
                logger.log(fromUserName, event, "Subscribe", map.get("Ticket"));
            } else {
                logger.log(fromUserName, event, "Subscribe", "success");
            }
        }
        //取消关注事件
        if (EventType.UNSUBSCRIBE_EVENT.equals(event)) {
            //取消关注即解绑openId
            logger.log(fromUserName, event, "Unsubscribe", "success");
        }
        //已关注时扫描二维码事件
        if (EventType.SCAN_EVENT.equals(event)) {
            //扫描带参数二维码用作各类营销宣传数据统计
            String eventKey = map.get("EventKey");
            logger.log(fromUserName, event, "Scan", eventKey);
        }
        //上报地理位置事件
        if (EventType.LOCATION_EVENT.equals(event)) {
            logger.log(fromUserName, event, "Location", map.get("Latitude") + "," +
                    map.get("Longitude") + "," + map.get("Precision"));
        }
        //菜单点击事件
        if (EventType.CLICK_EVENT.equals(event)) {
            String eventKey = map.get("EventKey");
            logger.log(fromUserName, event, "Click", eventKey);
        }
        //菜单查看事件,返回url
        if (EventType.VIEW_EVENT.equals(event)) {
            String eventKey = map.get("EventKey");
            logger.log(fromUserName, event, "View", eventKey);
        }
        return "";
    }
}
