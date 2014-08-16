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

package com.oakhole.channel.web;

import com.oakhole.channel.entity.Channel;
import com.oakhole.channel.service.ChannelService;
import com.oakhole.utils.Servlets;
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
@RequestMapping("/channel")
public class ChannelController {

    private static Logger logger = LoggerFactory.getLogger(ChannelService.class);

    @Autowired
    private ChannelService channelService;

    @RequestMapping(value = {"", "list"})
    public String index(HttpServletRequest request, Model model) {

        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        model.addAttribute("channels", this.channelService.findAll(searchParams));
        return "channel/index";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create() {
        return "channel/create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@RequestParam Channel channel, RedirectAttributes redirectAttributes) {
        this.channelService.save(channel);
        redirectAttributes.addFlashAttribute("message", "添加成功");
        return "redirect:/channel";
    }

    @RequestMapping(value = "show/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("channel", channelService.get(id));
        return "channel/show";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model) {
        model.addAttribute("channel", channelService.get(id));
        return "channel/update";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute(value = "channel") Channel channel, RedirectAttributes redirectAttributes) {
        this.channelService.save(channel);
        redirectAttributes.addFlashAttribute("message", "更新成功");
        return "redirect:/channel";
    }

    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        this.channelService.remove(channelService.get(id));
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return "redirect:/channel";
    }

    @ModelAttribute
    public void getChannel(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("channel", this.channelService.get(id));
        }
    }
}
