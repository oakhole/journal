/*
 * Copyright (c) 2014. Power by http://oakhole.com .
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

package com.oakhole.core.uitls;

/**
 * 全局静态变量，用于系统控制等。
 *
 * @author Oakhole
 * @since 1.0
 */
public class Global {

    /**
     * 设置默认当前行为，用于处理菜单选项，并记录当前操作，与Menu.code相对应
     * <p/>
     * <p>当前系统为二级菜单，表现形式为{@code sys.user},其中，{@code sys}表示一级菜单，${@code user}表示二级菜单</p>
     */
    public static final String CURRENT_ACTION = "current_action";

    //设置默认当前演示模式,production,devolopement,test,sandbox
    public static final String CURRENT_MODE = "devolopement";

}
