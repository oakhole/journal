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

import com.oakhole.core.uitls.JsonMapper;
import com.oakhole.voa.entity.AccessToken;
import com.oakhole.voa.entity.UserInfo;
import com.oakhole.voa.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户管理,分组管理
 *
 * @author oakhole
 * @since 1.0
 */
@Service
public class UserService {

    //用户管理
    private static final String getUserInfo_url = "https://api.weixin.qq.com/cgi-bin/user/info" +
            "?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    private static final String getUserList_url = "https://api.weixin.qq.com/cgi-bin/user/get" +
            "?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
    //分组管理
    private static final String createGroup_url = "https://api.weixin.qq.com/cgi-bin/groups/create" +
            "?access_token=ACCESS_TOKEN";
    private static final String getGroups_url = "https://api.weixin.qq.com/cgi-bin/groups/get" +
            "?access_token=ACCESS_TOKEN";
    private static final String getUserGroup_url = "https://api.weixin.qq.com/cgi-bin/groups/getid" +
            "?access_token=ACCESS_TOKEN";
    private static final String updateGroup_url = "https://api.weixin.qq.com/cgi-bin/groups/update" +
            "?access_token=ACCESS_TOKEN";
    private static final String changeUserGroup_url = "https://api.weixin.qq.com/cgi-bin/groups/members/update" +
            "?access_token=ACCESS_TOKEN";


    @Autowired
    private AccessToken accessToken;


    /**
     * get请求userinfo
     *
     * @param openId
     * @return
     */
    public UserInfo getUserInfo(String openId) {
        JsonMapper jsonMapper = new JsonMapper();
        String uri = getUserInfo_url.replace("ACCESS_TOKEN", accessToken.getAccess_token());
        uri = uri.replace("OPENID", openId);
        String resString = HttpClientUtils.getString(uri);
        return jsonMapper.fromJson(resString, UserInfo.class);
    }
}
