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

import com.oakhole.voa.entity.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/applicationContext.xml",
        "classpath*:/applicationContext-voa.xml"})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Test
    public void testGetUserInfo() throws Exception {
        this.authService.generateAccessToken();
        UserInfo userInfo = this.userService.getUserInfo("otcWxuPvBN9GkjWufMtxuRl9YX_U");
        Assert.isTrue("0".equals(userInfo.getErrcode()), userInfo.getErrcode() + "," + userInfo.getErrmsg());
        System.out.println(userInfo.getNickname() + "," + userInfo.getCountry() + "," + userInfo.getProvince()
                + "," + userInfo.getCity());
    }
}