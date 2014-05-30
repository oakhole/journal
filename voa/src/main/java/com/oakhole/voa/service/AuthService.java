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
import com.oakhole.core.uitls.Digests;
import com.oakhole.core.uitls.Encodes;
import com.oakhole.core.uitls.StringUtils;
import com.oakhole.voa.entity.AccessToken;
import com.oakhole.voa.entity.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 所有来自微信的请求需要Authentication验证，所有https post请求需要AccessToken
 * <p>若token失效则重新获取</p>
 *
 * @author oakhole
 * @since 1.0
 */
@Service
public class AuthService {

    private static Logger logger = LoggerFactory.getLogger(AuthService.class);

    /**
     * 所有的GET请求先做验证
     * <p>
     * 加密/校验流程如下：
     * 1. 将token、timestamp、nonce三个参数进行字典序排序
     * 2. 将三个参数字符串拼接成一个字符串进行sha1加密
     * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     * </p>
     *
     * @param authentication
     * @return
     * @see #generateSignature
     * @see #validate
     */
    public boolean validate(Authentication authentication) {
        //生成签名然后对比接收签名参数，确认后返回echostr
        String signature = generateSignature(authentication);
        //3.对比signature
        if (!StringUtils.isEmpty(signature) && !StringUtils.isEmpty(authentication.getSignature())
                && signature.equals(authentication.getSignature())) {
            return true;
        }
        logger.error("正确签名:{}", signature);
        return false;
    }

    /**
     * 生成签名
     *
     * @param authentication
     * @return
     */
    private String generateSignature(Authentication authentication) {
        //todo:从memcached中读取注册开发者认证时的token
        String token = "oakhole";   //默认值，用于test验证
        if (!StringUtils.isEmpty(authentication.getTimestamp()) && !StringUtils.isEmpty(authentication.getNonce())) {
            List<String> source = Lists.newArrayList();
            source.add(token);
            source.add(authentication.getTimestamp());
            source.add(authentication.getNonce());
            //1.字典顺序排序
            Collections.sort(source);
            //2.拼接字符串进行sha1加密
            return Encodes.encodeHex(Digests.sha1(StringUtils.join(source, "").getBytes()));
        }
        return "";
    }

    /**
     * 请求获取新的token,存入memcached
     * <p>
     * http请求方式: GET
     * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID
     * &secret=APPSECRET
     * </p>
     *
     * @return
     */
    public String generateAccessToken() {
        //todo:通过读取数据库或memcache实例化AccessToken
        AccessToken accessToken = new AccessToken();
        //todo:通过httpclient发送get请求获取响应,验证后存入新的token
        return "";
    }

}
