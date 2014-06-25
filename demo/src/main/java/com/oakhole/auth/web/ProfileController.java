package com.oakhole.auth.web;

import com.oakhole.auth.entity.User;
import com.oakhole.auth.service.ShiroDbRealm;
import com.oakhole.auth.service.UserService;
import com.oakhole.core.uitls.Global;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author Oakhole
 * @since 1.0
 */
@SuppressWarnings("ALL")
@Controller
@RequestMapping("/profile")
@SessionAttributes(Global.CURRENT_ACTION)
public class ProfileController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String profile(Model model) {
        model.addAttribute("user", this.userService.getCurrentUser());
        model.addAttribute(Global.CURRENT_ACTION, "sys.profile");
        return "auth/user/profile";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute(value = "user")User user,RedirectAttributes redirectAttributes) {
        this.userService.updateUser(user);
        updateCurrentName(user.getName());
        redirectAttributes.addFlashAttribute("message", "更改个人信息成功");
        return "redirect:/";
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
