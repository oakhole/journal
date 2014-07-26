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
import com.oakhole.core.uitls.Servlets;
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
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    public String index(HttpServletRequest request, Model model) {
        Map<String, Object> searchParmas = Servlets.getParametersStartingWith(request, "search_");
        model.addAttribute("channels", this.channelService.findAll(searchParmas));
        return "channel/index";
    }

    public String create() {
        return "channel/create";
    }

    public String create(@RequestParam Channel channel) {
        this.channelService.create(channel);
        return "redirect:/channel";
    }

    @RequestMapping(value = "show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, @Valid @ModelAttribute(value = "channel") Channel channel, Model model) {
        model.addAttribute("channel", channel);
        return "channel/show";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable Long id, @Valid @ModelAttribute(value = "channel") Channel channel, Model model) {
        model.addAttribute(channel);
        return "channel/update";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute(value = "channel") Channel channel, RedirectAttributes redirectAttributes) {
        this.channelService.update(channel);
        redirectAttributes.addAttribute("message", "更新成功");
        return "channel/update";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id, @Valid @ModelAttribute(value = "channel") Channel channel, RedirectAttributes redirectAttributes) {
        this.channelService.delete(channel);
        return "redirect:/channel";
    }

    @ModelAttribute
    public void getChannel(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("channel", this.channelService.get(id));
        }
    }
}
