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

package com.oakhole.voa.service;

import com.oakhole.voa.entity.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/applicationContext.xml",
        "classpath*:/applicationContext-voa.xml"})
public class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void testCreateMenu() throws Exception {
        Menu menu = getMenu();
        menuService.createMenu(menu);
    }

    @Test
    public void testDeleteMenu() throws Exception {
        menuService.deleteMenu();
    }

    @Test
    public void testQueryMenu() throws Exception {
        System.out.println(menuService.queryMenu());
    }

    public Menu getMenu() {
        Menu.ClickButton button1 = new Menu.ClickButton();
        button1.setName("点击");
        button1.setType("click");
        button1.setKey("CLICK_WITH_KEY");
        Menu.ViewButton button2 = new Menu.ViewButton();
        button2.setName("视图");
        button2.setType("view");
        button2.setUrl("http://www.baidu.com");
        Menu.ComplexButton button3 = new Menu.ComplexButton();
        button3.setName("子菜单");
        Menu.ViewButton sub1 = new Menu.ViewButton();
        sub1.setName("视图");
        sub1.setType("view");
        sub1.setUrl("http://www.baidu.com");
        button3.getSub_button().add(sub1);

        Menu menu = new Menu();
        menu.getButton().add(button1);
        menu.getButton().add(button2);
        menu.getButton().add(button3);

        return menu;
    }
}