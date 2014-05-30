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

import com.oakhole.voa.entity.WeixinMessage.responseMessage.ResTextMessage;
import junit.framework.Assert;
import junit.framework.TestCase;

public class MessageUtilsTest extends TestCase {

    public void testTextMessageToXml() throws Exception {
        String xml = MessageUtils.textMessageToXml(new ResTextMessage("2314980-81934","xiaozi"));
        Assert.assertNotNull("Generate xml can not be null",xml);
    }

}