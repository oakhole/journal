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

import com.oakhole.auth.entity.Group;
import com.oakhole.auth.service.GroupService;
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
@RequestMapping("/group")
public class GroupController {

    private static Logger logger = LoggerFactory.getLogger(GroupService.class);

    @Autowired
    private GroupService groupService;

    @RequiresPermissions("group:view")
    @RequestMapping(value = {"", "list"})
    public String index(HttpServletRequest request, Model model) {

        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        model.addAttribute("groups", this.groupService.findAll(searchParams));
        return "group/index";
    }

    @RequiresPermissions("group:edit")
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create() {
        return "group/create";
    }

    @RequiresPermissions("group:edit")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@RequestParam Group group, RedirectAttributes redirectAttributes) {
        this.groupService.create(group);
        redirectAttributes.addAttribute("message", "添加成功");
        return "redirect:/group";
    }

    @RequiresPermissions("group:view")
    @RequestMapping(value = "show/{id}")
    public String show(@PathVariable Long id, @Valid @ModelAttribute("group") Group group, Model model) {
        model.addAttribute("group", group);
        return "group/show";
    }

    @RequiresPermissions("group:edit")
    @RequestMapping(value = "update", method = RequestMethod.GET)
    public String update(@PathVariable Long id, @Valid @ModelAttribute(value = "group") Group group, Model model) {
        model.addAttribute("group", group);
        return "group/update";
    }

    @RequiresPermissions("group:edit")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute(value = "group") Group group, RedirectAttributes redirectAttributes) {
        this.groupService.update(group);
        redirectAttributes.addAttribute("message", "更新成功");
        return "redirect:/group";
    }

    @RequiresPermissions("group:edit")
    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable Long id, @Valid @ModelAttribute(value = "group") Group group, RedirectAttributes redirectAttributes) {
        this.groupService.remove(group);
        redirectAttributes.addAttribute("message", "删除成功");
        return "redirect:/group";
    }

    @ModelAttribute
    public void getGroup(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("group", this.groupService.get(id));
        }
    }
}
