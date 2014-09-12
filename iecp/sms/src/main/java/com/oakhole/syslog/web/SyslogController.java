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

package com.oakhole.syslog.web;

import com.google.common.collect.Maps;
import com.oakhole.financial.entity.Financial;
import com.oakhole.financial.service.FinancialService;
import com.oakhole.syslog.entity.SignLog;
import com.oakhole.syslog.service.SignLogService;
import com.oakhole.utils.Servlets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletRequest;
import java.util.Map;

/**
 * @authro Oakhole
 * @since 1.0
 */
@RequestMapping("/syslog")
@Controller
public class SyslogController {

    private static Map<String,String> signTypes = Maps.newHashMap();

    static {
        signTypes.put("web", "网页");
        signTypes.put("interface","接口");
    }

    @Autowired
    private SignLogService signLogService;

    @Autowired
    private FinancialService financialService;

    /**
     * 登陆日志
     *
     * @return
     */
    @RequestMapping("")
    public String index(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                       @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                       @RequestParam(value = "sortDirection", defaultValue = "DESC") String sortDirection,
                       @RequestParam(value = "sortBy", defaultValue = "id") String sortBy, Model model,
                       ServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        Page<SignLog> signLogs = this.signLogService.findAll(searchParams, pageNumber, pageSize, sortDirection, sortBy);
        model.addAttribute("signLogs", signLogs);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        model.addAttribute("signTypes", signTypes);
        return "syslog/index";
    }

    /**
     * 消费日志,即financial type ＝ 0
     *
     * @return
     */
    @RequestMapping("withdraw")
    public String withdraw(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                        @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                        @RequestParam(value = "sortDirection", defaultValue = "DESC") String sortDirection,
                        @RequestParam(value = "sortBy", defaultValue = "id") String sortBy, Model model,
                        ServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        Page<Financial> financials = this.financialService.findAll(searchParams, pageNumber, pageSize, sortDirection, sortBy);
        model.addAttribute("financials", financials);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        return "syslog/withdraw";
    }

    /**
     * 充值日志, 即financial type = 1
     *
     * @return
     */
    @RequestMapping("income")
    public String income(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                           @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                           @RequestParam(value = "sortDirection", defaultValue = "DESC") String sortDirection,
                           @RequestParam(value = "sortBy", defaultValue = "id") String sortBy, Model model,
                           ServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        Page<Financial> financials = this.financialService.findAll(searchParams, pageNumber, pageSize, sortDirection, sortBy);
        model.addAttribute("financials", financials);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        return "syslog/income";
    }

}

