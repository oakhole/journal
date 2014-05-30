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

package com.oakhole.voa.entity.WeixinMessage.eventMessage;

/**
 * @author oakhole
 * @since 1.0
 */

/**
 * 区别于SubscribeEvent,该类为已关注时的推送信息
 */
public class ScanEvent extends EventMessage{
    private String EventKey;    //事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
    private String Ticket;      //二维码的ticket，可用来换取二维码图片

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
    }
}
