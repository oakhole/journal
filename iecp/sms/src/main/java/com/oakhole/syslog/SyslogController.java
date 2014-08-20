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

package com.oakhole.syslog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @authro Oakhole
 * @since 1.0
 */
@RequestMapping("/syslog")
@Controller
public class SyslogController {

    /**
     * 系统日志查询
     *
     * @return
     */
    @RequestMapping("")
    public String index() {
        return "syslog/index";
    }

    @RequestMapping("withdraw")
    public String log_withdraw() {
        return "syslog/withdraw";
    }

    @RequestMapping("income")
    public String log_income() {
        return "syslog/income";
    }

    @RequestMapping("interface")
    public String log_interface() {
        return "syslog/interface";
    }
}

