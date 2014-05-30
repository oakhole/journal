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

import com.oakhole.voa.entity.WeixinMessage.WeixinMessage;

/**
 * 区别于ScanEvent和SubscribeEvent，主要用于非二维码方式关注事件和取消关注
 * @author oakhole
 * @since 1.0
 */
public class EventMessage extends WeixinMessage{

    private String Event;   //由EventType决定由哪个子类执行,subscribe & unsubscribe由该类执行

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }
}
