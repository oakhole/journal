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

package com.oakhole.toolbox;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @authro Oakhole
 * @since 1.0
 */
@Controller
@RequestMapping("/toolbox")
public class ToolboxController {

    /**
     * 工具列表，主要为系统设置，人员管理，etc
     * @return
     */
    @RequestMapping("")
    public String index() {
        return "toolbox/index";
    }
}
