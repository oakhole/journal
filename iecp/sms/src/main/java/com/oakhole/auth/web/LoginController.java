package com.oakhole.auth.web;

import com.oakhole.auth.service.ShiroDbRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Administrator
 * @since 14-3-14
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String login() {
        ShiroDbRealm.ShiroUser shiroUser = (ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        if (shiroUser != null) {
            return "redirect:/";
        }
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username, Model model) {
        model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM,username);
        return "login";
    }

}
