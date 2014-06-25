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

package com.oakhole.financial.web;

import com.oakhole.core.uitls.Servlets;
import com.oakhole.financial.entity.Financial;
import com.oakhole.financial.service.FinancialService;
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
@RequestMapping("/financial")
public class FinancialController {

    @Autowired
    private FinancialService financialService;

    @RequestMapping("")
    public String index(HttpServletRequest request, Model model) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        model.addAttribute("financials", this.financialService.findAll(searchParams));
        return "financial/index";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create() {
        return "financial/create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@RequestParam Financial financial, RedirectAttributes redirectAttributes) {
        this.financialService.create(financial);
        redirectAttributes.addAttribute("message", "新增成功");
        return "redirect:/financial";
    }

    @RequestMapping(value = "show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, @Valid @ModelAttribute(value = "financial") Financial financial, Model model) {
        model.addAttribute("financial", financial);
        return "financial/show";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable Long id, @Valid @ModelAttribute(value = "financial") Financial financial, Model model) {
        model.addAttribute("financial", financial);
        return "financial/update";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute(value = "financial") Financial financial, RedirectAttributes redirectAttributes) {
        this.financialService.update(financial);
        redirectAttributes.addAttribute("message", "更新成功");
        return "redirect:/financial";
    }

    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable Long id, @Valid @ModelAttribute(value = "financial") Financial financial, RedirectAttributes redirectAttributes) {
        this.financialService.delete(financial);
        redirectAttributes.addAttribute("message", "删除成功");
        return "redirect:/financial";
    }

    @ModelAttribute
    public void getFinancial(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("financial", this.financialService.get(id));
        }
    }
}
