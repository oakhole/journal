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

import com.oakhole.auth.entity.Perm;
import com.oakhole.auth.service.PermService;
import com.oakhole.utils.Servlets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * @author Oakhole
 * @since 1.0
 */
@Controller
@RequestMapping("/perm")
public class PermController {

    private static Logger logger = LoggerFactory.getLogger(PermService.class);

    @Autowired
    private PermService permService;

     @RequestMapping(value = {"", "list"})
        public String list(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                           @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                           @RequestParam(value = "sortDirection", defaultValue = "DESC") String sortDirection,
                           @RequestParam(value = "sortBy", defaultValue = "id") String sortBy, Model model,
                           ServletRequest request) {
            Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
            Page<Perm> perms = this.permService.findAll(searchParams, pageNumber, pageSize,sortDirection,sortBy);
            model.addAttribute("perms", perms);
            model.addAttribute("pageNumber", pageNumber);
            model.addAttribute("pageSize", pageSize);
            model.addAttribute("sortDirection", sortDirection);
            model.addAttribute("sortBy", sortBy);
            model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
            return "perm/index";
        }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create() {
        return "perm/create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Valid Perm perm, RedirectAttributes redirectAttributes) {
        this.permService.save(perm);
        redirectAttributes.addFlashAttribute("message", "添加成功");
        redirectAttributes.addFlashAttribute("returnStatus", "success");
        return "redirect:/perm";
    }

    @RequestMapping(value = "show/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("perm", permService.get(id));
        return "perm/show";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model) {
        model.addAttribute("perm", permService.get(id));
        return "perm/update";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute(value = "perm") Perm perm, RedirectAttributes redirectAttributes) {
        this.permService.save(perm);
        redirectAttributes.addFlashAttribute("message", "更新成功");
        redirectAttributes.addFlashAttribute("returnStatus", "success");
        return "redirect:/perm";
    }

    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        this.permService.remove(permService.get(id));
        redirectAttributes.addFlashAttribute("message", "删除成功");
        redirectAttributes.addFlashAttribute("returnStatus", "success");
        return "redirect:/perm";
    }

    @ModelAttribute
    public void getPerm(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("perm", this.permService.get(id));
        }
    }
}
