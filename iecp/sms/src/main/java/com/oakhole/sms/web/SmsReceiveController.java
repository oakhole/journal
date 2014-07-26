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
import com.oakhole.sms.entity.SmsReceive;
import com.oakhole.sms.service.SmsReceiveService;
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
@RequestMapping("/sms/receive")
public class SmsReceiveController {

    @Autowired
    private SmsReceiveService smsReceiveService;

    @RequestMapping(value = "")
    public String index(HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        model.addAttribute("smsReceiveList", this.smsReceiveService.findAll(searchParams));
        return "sms/receive/index";
    }

    @RequestMapping(value = "show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, @Valid @ModelAttribute SmsReceive smsReceive, Model model) {
        model.addAttribute("smsReceive", smsReceive);
        return "sms/receive/show";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create() {
        return "sms/receive/create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@RequestParam SmsReceive smsReceive, RedirectAttributes redirectAttributes) {
        this.smsReceiveService.create(smsReceive);
        redirectAttributes.addAttribute("message", "新建成功");
        return "redirect:/sms/receive";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable Long id, @Valid @ModelAttribute SmsReceive smsReceive, Model model) {
        model.addAttribute("smsReceive", smsReceive);
        return "sms/receive/update";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute SmsReceive smsReceive, RedirectAttributes redirectAttributes) {
        this.smsReceiveService.update(smsReceive);
        redirectAttributes.addAttribute("message", "更新成功");
        return "redirect:/sms/receive";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, @Valid @ModelAttribute SmsReceive smsReceive, RedirectAttributes redirectAttributes) {
        this.smsReceiveService.delete(smsReceive);
        redirectAttributes.addAttribute("message", "删除成功");
        return "redirect:/sms/receive";
    }

    @ModelAttribute
    public void getSmsReceive(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {

        if (id != -1) {
            model.addAttribute("smsReceive", this.smsReceiveService.get(id));
        }
    }
}
