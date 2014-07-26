package com.oakhole.auth.web;

import com.oakhole.auth.entity.User;
import com.oakhole.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author Administrator
 * @since 14-3-26
 */
@SuppressWarnings("ALL")
@Controller
@RequestMapping("/register")
public class RegistController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String registForm() {
        return "auth/user/register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String regist(@Valid User user, RedirectAttributes redirectAttributes) {
        this.userService.registUser(user);
        redirectAttributes.addFlashAttribute("username", user.getUsername());
        return "redirect:/user";
    }

    @RequestMapping("checkUsername")
    @ResponseBody
    public String checkUsername(@RequestParam(value = "username") String username) {

        User user = this.userService.findUserByUsername(username);
        if (user == null) {
            return "true";
        }
        return "false";
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
