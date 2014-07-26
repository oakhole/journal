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

package ${packageName}.web;

import ${packageName}.entity.${ClassName};
import ${packageName}.service.${ClassName}Service;
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
 * @author ${author}
 * @since ${since}
 */
@Controller
@RequestMapping("/${className}")
public class ${ClassName}Controller {

    private static Logger logger = LoggerFactory.getLogger(${ClassName}Service.class);

    @Autowired
    private ${ClassName}Service ${className}Service;

    @RequiresPermissions("${className}:view")
    @RequestMapping(value = {"", "list"})
    public String index(HttpServletRequest request, Model model) {

        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        model.addAttribute("${className}s", this.${className}Service.findAll(searchParams));
        return "${className}/index";
    }

    @RequiresPermissions("${className}:edit")
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create() {
        return "${className}/create";
    }

    @RequiresPermissions("${className}:edit")
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@RequestParam ${ClassName} ${className}, RedirectAttributes redirectAttributes) {
        this.${className}Service.create(${className});
        redirectAttributes.addAttribute("message", "添加成功");
        return "redirect:/${className}";
    }

    @RequiresPermissions("${className}:view")
    @RequestMapping(value = "show/{id}")
    public String show(@PathVariable Long id, @Valid @ModelAttribute("${className}") ${ClassName} ${className}, Model model) {
        model.addAttribute("${className}", ${className});
        return "${className}/show";
    }

    @RequiresPermissions("${className}:edit")
    @RequestMapping(value = "update", method = RequestMethod.GET)
    public String update(@PathVariable Long id, @Valid @ModelAttribute(value = "${className}") ${ClassName} ${className}, Model model) {
        model.addAttribute("${className}", ${className});
        return "${className}/update";
    }

    @RequiresPermissions("${className}:edit")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute(value = "${className}") ${ClassName} ${className}, RedirectAttributes redirectAttributes) {
        this.${className}Service.update(${className});
        redirectAttributes.addAttribute("message", "更新成功");
        return "redirect:/${className}";
    }

    @RequiresPermissions("${className}:edit")
    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable Long id, @Valid @ModelAttribute(value = "${className}") ${ClassName} ${className}, RedirectAttributes redirectAttributes) {
        this.${className}Service.remove(${className});
        redirectAttributes.addAttribute("message", "删除成功");
        return "redirect:/${className}";
    }

    @ModelAttribute
    public void get${ClassName}(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("${className}", this.${className}Service.get(id));
        }
    }
}
