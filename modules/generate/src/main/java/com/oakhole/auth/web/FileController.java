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

import com.oakhole.auth.entity.File;
import com.oakhole.auth.service.FileService;
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
@RequestMapping("/file")
public class FileController {

    private static Logger logger = LoggerFactory.getLogger(FileService.class);

    @Autowired
    private FileService fileService;

    @RequiresPermissions("file:view")
    @RequestMapping(value = {"", "list"})
    public String index(HttpServletRequest request, Model model) {

        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        model.addAttribute("files", this.fileService.findAll(searchParams));
        return "file/index";
    }

    @RequiresPermissions("file:edit")
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create() {
        return "file/create";
    }

    @RequiresPermissions("file:edit")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@RequestParam File file, RedirectAttributes redirectAttributes) {
        this.fileService.create(file);
        redirectAttributes.addAttribute("message", "添加成功");
        return "redirect:/file";
    }

    @RequiresPermissions("file:view")
    @RequestMapping(value = "show/{id}")
    public String show(@PathVariable Long id, @Valid @ModelAttribute("file") File file, Model model) {
        model.addAttribute("file", file);
        return "file/show";
    }

    @RequiresPermissions("file:edit")
    @RequestMapping(value = "update", method = RequestMethod.GET)
    public String update(@PathVariable Long id, @Valid @ModelAttribute(value = "file") File file, Model model) {
        model.addAttribute("file", file);
        return "file/update";
    }

    @RequiresPermissions("file:edit")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute(value = "file") File file, RedirectAttributes redirectAttributes) {
        this.fileService.update(file);
        redirectAttributes.addAttribute("message", "更新成功");
        return "redirect:/file";
    }

    @RequiresPermissions("file:edit")
    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable Long id, @Valid @ModelAttribute(value = "file") File file, RedirectAttributes redirectAttributes) {
        this.fileService.remove(file);
        redirectAttributes.addAttribute("message", "删除成功");
        return "redirect:/file";
    }

    @ModelAttribute
    public void getFile(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("file", this.fileService.get(id));
        }
    }
}
