/*
 * Copyright (c) 2013-2014. Powered by http://oakhole.com .
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.oakhole.auth.web;

import com.oakhole.auth.service.ShiroDbRealm;
import com.oakhole.syslog.entity.SignLog;
import com.oakhole.syslog.service.SignLogService;
import com.oakhole.utils.Calendars;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletRequest;

/**
 * @author Oakhole
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private SignLogService signLogService;

    @RequestMapping(method = RequestMethod.GET)
    public String login(ServletRequest servletRequest) {
        ShiroDbRealm.ShiroUser shiroUser = (ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        if (shiroUser != null) {

            // 存入登陆日志
            SignLog signLog = new SignLog();
            signLog.setUsername(shiroUser.getLoginName());
            signLog.setSignTime(Calendars.getNow());
            signLog.setSignIp(servletRequest.getRemoteAddr());
            signLog.setSignType("web");
            signLogService.save(signLog);

            return "redirect:/";
        }
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username, Model model) {
        model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
        return "login";
    }

}
