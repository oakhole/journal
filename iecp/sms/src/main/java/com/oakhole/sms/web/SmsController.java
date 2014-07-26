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

package com.oakhole.sms.web;

import com.oakhole.core.uitls.Servlets;
import com.oakhole.sms.entity.Sms;
import com.oakhole.sms.service.SmsService;
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
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @RequestMapping("")
    public String index(HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        model.addAttribute("smsList", this.smsService.findAll(searchParams));
        return "sms/index";
    }

    public String create() {
        return "sms/create";
    }

    public String create(@RequestParam Sms sms, RedirectAttributes redirectAttributes) {
        this.smsService.create(sms);
        redirectAttributes.addAttribute("message", "新建成功");
        return "redirect:/sms";
    }

    @RequestMapping(value = "show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, @Valid @ModelAttribute Sms sms, Model model) {
        model.addAttribute("sms", sms);
        return "sms/show";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable Long id, @Valid @ModelAttribute Sms sms, Model model) {
        model.addAttribute("sms", sms);
        return "sms/update";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute Sms sms, RedirectAttributes redirectAttributes) {
        this.smsService.update(sms);
        redirectAttributes.addAttribute("message", "更新成功");
        return "redirect:/sms";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, @Valid @ModelAttribute Sms sms, RedirectAttributes redirectAttributes) {
        this.smsService.delete(sms);
        redirectAttributes.addAttribute("message", "删除成功");
        return "redirect:/sms";
    }

    @ModelAttribute
    public void getSms(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("sms", this.smsService.get(id));
        }
    }
}
