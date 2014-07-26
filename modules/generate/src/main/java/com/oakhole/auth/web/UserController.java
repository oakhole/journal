package com.oakhole.auth.web;

import com.google.common.collect.Maps;
import com.oakhole.auth.entity.User;
import com.oakhole.auth.service.UserService;
import com.oakhole.core.uitls.Servlets;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @since 14-3-7
 */
@SuppressWarnings("ALL")
@Controller
@RequestMapping("/user")
public class UserController {

    private static Map<String, String> allStatus = Maps.newHashMap();

    @Autowired
    private UserService userService;

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", this.userService.getUser(id));
        model.addAttribute("allStatus", allStatus);
        model.addAttribute("allRoles", this.userService.getAllRole());

        return "auth/user/update";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute(value = "user") User user, RedirectAttributes redirectAttributes) {
        this.userService.updateUser(user);
        redirectAttributes.addFlashAttribute("message", "更新用户" + user.getUsername() + "成功");
        return "redirect:/user";
    }

    @RequiresRoles(value = {"Admin"})
    @RequestMapping(value = "")
    public String list(Model model, ServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        List<User> users = this.userService.searchUser(searchParams);
        model.addAttribute("users", users);
        return "auth/user/list";
    }

    /**
     * toggle所选用户的状态
     * @param ids
     * @param redirectAttributes
     * @return
     */
    @RequiresRoles(value = {"Admin"})
    @RequestMapping(value = "delete")
    public String delete(@RequestParam("ids") String ids,RedirectAttributes redirectAttributes) {
        for (String id : ids.split(",")) {
            this.userService.deleteUser(Long.valueOf(id));
        }
        redirectAttributes.addFlashAttribute("returnStatus", "attention");
        redirectAttributes.addFlashAttribute("message","删除成功，注：本系统不删除任何数据，仅作状态更改");
        return "redirect:/user";
    }

    @ModelAttribute
    public void getUser(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("user", this.userService.getUser(id));
        }
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("roleList");
    }
}
