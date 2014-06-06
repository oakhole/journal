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

import com.google.common.collect.Maps;
import com.oakhole.core.uitls.JsonMapper;
import com.oakhole.voa.entity.AccessToken;
import com.oakhole.voa.entity.Menu;
import com.oakhole.voa.utils.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author oakhole
 * @since 1.0
 */
@Service
public class MenuService {

    private Logger logger = LoggerFactory.getLogger(MenuService.class);

    @Autowired
    private AccessToken accessToken;

    @Autowired
    private AuthService authService;

    private static final String queryUrl = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    private static final String deleteUrl = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    private static final String createUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * 新增menu，两个方向，一个是提交post请求，一个存入数据库
     *
     * @param menu
     */
    public void createMenu(Menu menu) {
        JsonMapper jsonMapper = new JsonMapper();
        authService.generateAccessToken();
        String uri = createUrl.replace("ACCESS_TOKEN", accessToken.getAccess_token());
        String menuJson = jsonMapper.toJson(menu);
        String resInfo = HttpClientUtils.post(uri, menuJson);
        logger.info("新建菜单:{}",resInfo);
    }

    public void deleteMenu() {

        authService.generateAccessToken();
        String uri = deleteUrl.replace("ACCESS_TOKEN", accessToken.getAccess_token());
        Map<String, String> params = Maps.newHashMap();
        String resInfo = HttpClientUtils.getString(uri);
        logger.info("删除菜单:{}", resInfo);
    }

    /**
     * 远程查询当前菜单,get方法
     *
     * @return
     */
    public String queryMenu() {
        authService.generateAccessToken();
        String uri = queryUrl.replace("ACCESS_TOKEN", accessToken.getAccess_token());
        return HttpClientUtils.getString(uri);
    }
}
