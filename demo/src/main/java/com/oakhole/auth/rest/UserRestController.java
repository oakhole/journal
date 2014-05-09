package com.oakhole.auth.rest;

import com.oakhole.auth.entity.Menu;
import com.oakhole.auth.entity.User;
import com.oakhole.auth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Administrator
 * @since 14-3-7
 */
@SuppressWarnings("ALL")
@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private static Logger logger = LoggerFactory.getLogger(UserRestController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User findUserById(@PathVariable long id, Model model) {
        User user = this.userService.getUser(id);
        if (user == null) {
            this.logger.warn("id为{}时找不到任何用户!", id);
            return null;
        }
        return user;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> list() {
        return this.userService.getUserList();
    }

    @RequestMapping(value = "menu")
    public List<Menu> getMenu() {
        return this.userService.getMenuTree(this.userService.getCurrentUser());
    }
}
