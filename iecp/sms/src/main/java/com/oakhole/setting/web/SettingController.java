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

package com.oakhole.setting.web;

import com.oakhole.core.uitls.Servlets;
import com.oakhole.setting.entity.Setting;
import com.oakhole.setting.service.SettingService;
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
@RequestMapping("setting")
public class SettingController {

    @Autowired
    private SettingService settingService;

    @RequestMapping("")
    public String index(HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        model.addAttribute("settings", this.settingService.findAll(searchParams));
        return "setting/index";
    }

    @RequestMapping(value = "show", method = RequestMethod.GET)
    public String show(@PathVariable Long id, @Valid @ModelAttribute(value = "setting") Setting setting, Model model) {
        model.addAttribute("setting", setting);
        return "setting/show";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create() {
        return "setting/create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@RequestParam Setting setting, RedirectAttributes redirectAttributes) {
        this.settingService.create(setting);
        redirectAttributes.addAttribute("message", "新建成功");
        return "redirect:/setting";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable Long id, @Valid @ModelAttribute(value = "setting") Setting setting, Model model) {
        model.addAttribute("setting", setting);
        return "setting/update";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute(value = "setting") Setting setting, RedirectAttributes redirectAttributes) {
        this.settingService.update(setting);
        redirectAttributes.addAttribute("message", "更新成功");
        return "redirect:/setting/update";
    }


    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, @Valid @ModelAttribute(value = "setting") Setting setting, RedirectAttributes redirectAttributes) {
        this.settingService.delete(setting);
        redirectAttributes.addAttribute("message", "删除成功");
        return "redirect:/setting";
    }

    @ModelAttribute
    public void getSetting(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("setting", this.settingService.get(id));
        }
    }
}
