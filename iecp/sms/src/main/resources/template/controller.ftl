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
 * @author ${author}
 * @since ${since}
 */
@Controller
@RequestMapping("/${className}")
public class ${ClassName}Controller {

    private static Logger logger = LoggerFactory.getLogger(${ClassName}Service.class);

    @Autowired
    private ${ClassName}Service ${className}Service;

     @RequestMapping(value = {"", "list"})
        public String list(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                           @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                           @RequestParam(value = "sortDirection", defaultValue = "DESC") String sortDirection,
                           @RequestParam(value = "sortBy", defaultValue = "id") String sortBy, Model model,
                           ServletRequest request) {
            Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
            Page<${ClassName}> ${className}s = this.${className}Service.findAll(searchParams, pageNumber, pageSize,sortDirection,sortBy);
            model.addAttribute("${className}s", ${className}s);
            model.addAttribute("pageNumber", pageNumber);
            model.addAttribute("pageSize", pageSize);
            model.addAttribute("sortDirection", sortDirection);
            model.addAttribute("sortBy", sortBy);
            model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
            return "${className}/index";
        }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create() {
        return "${className}/create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Valid ${ClassName} ${className}, RedirectAttributes redirectAttributes) {
        this.${className}Service.save(${className});
        redirectAttributes.addFlashAttribute("message", "添加成功");
        redirectAttributes.addFlashAttribute("returnStatus", "success");
        return "redirect:/${className}";
    }

    @RequestMapping(value = "show/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("${className}", ${className}Service.get(id));
        return "${className}/show";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model) {
        model.addAttribute("${className}", ${className}Service.get(id));
        return "${className}/update";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute(value = "${className}") ${ClassName} ${className}, RedirectAttributes redirectAttributes) {
        this.${className}Service.save(${className});
        redirectAttributes.addFlashAttribute("message", "更新成功");
        redirectAttributes.addFlashAttribute("returnStatus", "success");
        return "redirect:/${className}";
    }

    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        this.${className}Service.remove(${className}Service.get(id));
        redirectAttributes.addFlashAttribute("message", "删除成功");
        redirectAttributes.addFlashAttribute("returnStatus", "success");
        return "redirect:/${className}";
    }

    @ModelAttribute
    public void get${ClassName}(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("${className}", this.${className}Service.get(id));
        }
    }
}
