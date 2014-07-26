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

package com.oakhole.advice.web;

import com.oakhole.advice.entity.Advice;
import com.oakhole.advice.service.AdviceService;
import com.oakhole.core.uitls.Servlets;
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
 * @author oakhole
 * @since 1.0
 */
@Controller
@RequestMapping("/advice")
public class AdviceController {

    @Autowired
    private AdviceService adviceService;

    @RequiresPermissions("advice:view")
    @RequestMapping(value = {"", "list"})
    public String index(HttpServletRequest request, Model model) {

        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        model.addAttribute("advices", this.adviceService.findAll(searchParams));
        return "advice/index";
    }

    @RequiresPermissions("advice:edit")
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create() {
        return "advice/create";
    }

    @RequiresPermissions("advice:edit")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@RequestParam Advice advice, RedirectAttributes redirectAttributes) {
        this.adviceService.create(advice);
        redirectAttributes.addAttribute("message", "添加成功");
        return "redirect:/advice";
    }

    @RequiresPermissions("advice:view")
    @RequestMapping(value = "show/{id}")
    public String show(@PathVariable Long id, @Valid @ModelAttribute("advice") Advice advice, Model model) {
        model.addAttribute("advice", advice);
        return "advice/show";
    }

    @RequiresPermissions("advice:edit")
    @RequestMapping(value = "update", method = RequestMethod.GET)
    public String update(@PathVariable Long id, @Valid @ModelAttribute(value = "advice") Advice advice, Model model) {
        model.addAttribute("advice", advice);
        return "advice/update";
    }

    @RequiresPermissions("advice:edit")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute(value = "advice") Advice advice, RedirectAttributes redirectAttributes) {
        this.adviceService.update(advice);
        redirectAttributes.addAttribute("message", "更新成功");
        return "redirect:/advice";
    }

    @RequiresPermissions("advice:edit")
    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable Long id, @Valid @ModelAttribute(value = "advice") Advice advice, RedirectAttributes redirectAttributes) {
        this.adviceService.remove(advice);
        redirectAttributes.addAttribute("message", "删除成功");
        return "redirect:/advice";
    }

    @ModelAttribute
    public void getAdvice(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("advice", this.adviceService.get(id));
        }
    }
}
