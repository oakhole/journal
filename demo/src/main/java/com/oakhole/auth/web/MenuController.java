/*
 * Copyright (c) 2014. Power by http://oakhole.com .
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

package com.oakhole.auth.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.oakhole.auth.dto.MenuDTO;
import com.oakhole.auth.entity.Menu;
import com.oakhole.auth.entity.User;
import com.oakhole.auth.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * @author Oakhole
 * @since 1.0
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private UserService userService;

    /**
     * 读取当前用户的菜单，用于首页菜单刷新
     * @return
     */
    @RequestMapping(value = "/current",method = RequestMethod.GET)
    @ResponseBody
    public Set<MenuDTO> getMenus() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        Set<MenuDTO> menus = Sets.newTreeSet();
        MenuDTO dto = null;
        for (Menu menu : this.userService.getMenu()) {
            dto = dozerBeanMapper.map(menu,MenuDTO.class);
            dto.setpId(menu.getParent() == null ? 0 : menu.getParent().getId());
            dto.setHasChild(menu.getChildList().size()>0);
            dto.setChecked(false);
            dto.setOpen(false);
            menus.add(dto);
        }
        return menus;
    }

    /**
     * 按用户查找，用于权限分配
     * @param user
     * @return
     */
    @RequiresRoles("Admin")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Set<MenuDTO> getMenus(@Valid @ModelAttribute("user")User user) {
        Set<Menu> sourceMenus = this.userService.getMenu(user);
        List<Menu> allMenus = this.userService.getAllMenu();
        Set<MenuDTO> menus = Sets.newTreeSet();
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        MenuDTO dto = null;
        for (Menu menu : allMenus) {
            dto = dozerBeanMapper.map(menu, MenuDTO.class);
            dto.setpId(menu.getParent() == null ? 0 : menu.getParent().getId());
            // 得到所有的菜单列表，与当前用户菜单对比，有则checked
            if (sourceMenus.contains(menu)) {
                dto.setChecked(true);
            }
            menus.add(dto);
        }
        return menus;
    }

    @ModelAttribute
    public void getUser(@RequestParam(value = "id",defaultValue = "-1")long id,Model model) {
        if (id != -1) {
            model.addAttribute("user", this.userService.getUser(id));
        }
    }
}
