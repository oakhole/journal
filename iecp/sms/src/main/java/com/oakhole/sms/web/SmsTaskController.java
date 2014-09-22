/*
 * Copyright (c) 2013-2014. Powered by http://oakhole.com .
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.oakhole.sms.web;

import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.oakhole.auth.entity.File;
import com.oakhole.auth.service.ShiroDbRealm;
import com.oakhole.auth.service.UserService;
import com.oakhole.sms.entity.SmsTask;
import com.oakhole.sms.service.SmsTaskService;
import com.oakhole.utils.Calendars;
import com.oakhole.utils.Servlets;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.UUID;

/**
 * @author Oakhole
 * @since 1.0
 */
@Controller
@RequestMapping("/smsTask")
public class SmsTaskController {

    private static Logger logger = LoggerFactory.getLogger(SmsTaskService.class);

    private static Map<String, String> allSendStatus = Maps.newHashMap();
    private static Map<String, String> allSendStatusLabel = Maps.newHashMap();

    static {
        allSendStatus.put("0", "待发送");
        allSendStatus.put("1", "分包处理");
        allSendStatus.put("2", "发送完成");
        allSendStatus.put("3", "发送失败");
    }

    static {
        allSendStatusLabel.put("0", "default");
        allSendStatusLabel.put("1", "warning");
        allSendStatusLabel.put("2", "success");
        allSendStatusLabel.put("3", "danger");
    }

    @Autowired
    private UserService userService;

    @Autowired
    private SmsTaskService smsTaskService;

    @RequestMapping(value = {"", "list"})
    public String list(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                       @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                       @RequestParam(value = "sortDirection", defaultValue = "DESC") String sortDirection,
                       @RequestParam(value = "sortBy", defaultValue = "id") String sortBy, Model model,
                       ServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        Page<SmsTask> smsTasks = this.smsTaskService.findAll(searchParams, pageNumber, pageSize, sortDirection, sortBy);
        model.addAttribute("smsTasks", smsTasks);
        model.addAttribute("allSendStatusLabel", allSendStatusLabel);
        model.addAttribute("allSendStatus", allSendStatus);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        return "smsTask/index";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create() {
        return "smsTask/create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Valid SmsTask smsTask, @RequestParam String phoneNumbers, RedirectAttributes redirectAttributes) {

        // 发送时间
        smsTask.setSendTime(Calendars.getNow());

        // 将号码存入文件中
        String fileName = UUID.randomUUID().toString();
        String fileUrl = "/Users/Oakhole/Desktop/phonefile/" + fileName;
        java.io.File readFile = new java.io.File(fileUrl);

        try {
            FileUtils.writeStringToFile(readFile, phoneNumbers);
        } catch (IOException e) {
            logger.error("号码文件写入错误,{}", e.getMessage());
        }

        File phone_attachment = new File();
        phone_attachment.setName(fileName);
        phone_attachment.setCode("644");
        phone_attachment.setUrl(fileUrl);

        smsTask.setPhone_attachment(phone_attachment);

        // 关联发送用户
        ShiroDbRealm.ShiroUser shiroUser = (ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        smsTask.setOwner(this.userService.findUserByUsername(shiroUser.getLoginName()));

        // 初始化设置发送状态 0：待发送
        smsTask.setSendStatus("0");

        this.smsTaskService.save(smsTask);
        redirectAttributes.addFlashAttribute("message", "添加成功");
        redirectAttributes.addFlashAttribute("returnStatus", "success");
        return "redirect:/smsTask";
    }

    @RequestMapping(value = "show/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("smsTask", smsTaskService.get(id));
        return "smsTask/show";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model) {
        model.addAttribute("smsTask", smsTaskService.get(id));
        model.addAttribute("allSendStatus", allSendStatus);
        return "smsTask/update";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute(value = "smsTask") SmsTask smsTask, RedirectAttributes redirectAttributes) {
        this.smsTaskService.save(smsTask);
        redirectAttributes.addFlashAttribute("message", "更新成功");
        redirectAttributes.addFlashAttribute("returnStatus", "success");
        return "redirect:/smsTask";
    }

    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        this.smsTaskService.remove(smsTaskService.get(id));
        redirectAttributes.addFlashAttribute("message", "删除成功");
        redirectAttributes.addFlashAttribute("returnStatus", "success");
        return "redirect:/smsTask";
    }

    @ResponseBody
    @RequestMapping(value = "uploadPhoneFile")
    public String uploadPhoneFile(@RequestParam(value = "fileupload", required = false) MultipartFile fileupload) {
//        // todo: 读取文件内容返回json数据
//        try {
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileupload.getInputStream()));
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "fail: " + e.getMessage();
//        }

        String phoneNumbers = "";

        try {
            phoneNumbers = String.valueOf(fileupload.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return "fail: " + e.getMessage();
        }
        return "success: " + phoneNumbers;
    }

    @ModelAttribute
    public void getSmsTask(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("smsTask", this.smsTaskService.get(id));
        }
    }
}
