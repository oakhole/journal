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

package com.oakhole.auth.web;

import com.oakhole.auth.entity.Role;
import com.oakhole.auth.service.RoleService;
import com.oakhole.core.uitls.Servlets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * @author Oakhole
 * @since 1.0
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    private static Logger logger = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    private RoleService roleService;

    @RequiresPermissions("role:view")
    @RequestMapping(value = {"", "list"})
    public String index(HttpServletRequest request, Model model) {

        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        model.addAttribute("roles", this.roleService.findAll(searchParams));
        return "role/index";
    }

    @RequiresPermissions("role:edit")
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create() {
        return "role/create";
    }

    @RequiresPermissions("role:edit")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@RequestParam Role role, RedirectAttributes redirectAttributes) {
        this.roleService.create(role);
        redirectAttributes.addAttribute("message", "添加成功");
        return "redirect:/role";
    }

    @RequiresPermissions("role:view")
    @RequestMapping(value = "show/{id}")
    public String show(@PathVariable Long id, @Valid @ModelAttribute("role") Role role, Model model) {
        model.addAttribute("role", role);
        return "role/show";
    }

    @RequiresPermissions("role:edit")
    @RequestMapping(value = "update", method = RequestMethod.GET)
    public String update(@PathVariable Long id, @Valid @ModelAttribute(value = "role") Role role, Model model) {
        model.addAttribute("role", role);
        return "role/update";
    }

    @RequiresPermissions("role:edit")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute(value = "role") Role role, RedirectAttributes redirectAttributes) {
        this.roleService.update(role);
        redirectAttributes.addAttribute("message", "更新成功");
        return "redirect:/role";
    }

    @RequiresPermissions("role:edit")
    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable Long id, @Valid @ModelAttribute(value = "role") Role role, RedirectAttributes redirectAttributes) {
        this.roleService.remove(role);
        redirectAttributes.addAttribute("message", "删除成功");
        return "redirect:/role";
    }

    @ModelAttribute
    public void getRole(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("role", this.roleService.get(id));
        }
    }
}
