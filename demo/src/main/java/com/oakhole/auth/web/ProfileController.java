package com.oakhole.auth.web;

import com.oakhole.auth.entity.User;
import com.oakhole.auth.service.ShiroDbRealm;
import com.oakhole.auth.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author Administrator
 * @since 14-3-27
 */
@SuppressWarnings("ALL")
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String profile(Model model) {
        model.addAttribute("user", getCurrentUser());
        return "auth/user/profile";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute(value = "user")User user,RedirectAttributes redirectAttributes) {
        this.userService.updateUser(user);
        updateCurrentName(user.getName());
        redirectAttributes.addFlashAttribute("message", "更改个人信息成功");
        return "redirect:/";
    }

    /**
     * obtain current user
     * @return
     */
    private User getCurrentUser() {
        ShiroDbRealm.ShiroUser shiroUser = (ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return this.userService.findUserByUsername(shiroUser.loginName);
    }

    @ModelAttribute
    public void getUser(@RequestParam(value = "id",defaultValue = "-1") long id,Model model) {
       User user =  this.userService.getUser(id);
        model.addAttribute("user", user);
    }

    /**
     * 更新当前用户的用户名
     * @param name
     */
    private void updateCurrentName(String name) {
        ShiroDbRealm.ShiroUser shiroUser = (ShiroDbRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
        shiroUser.name = name;
    }
}
