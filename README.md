[Journal][]
==
[![Build Status](https://travis-ci.org/oakhole/journal.svg?branch=master)](https://travis-ci.org/oakhole/journal)

##About

之所以要写`journal`，很大一部分原因是为了方便自己和他人更好的了解较新的技术架构及业务需求

    I've own attitude though as a newer .

>Announce:所有代码均仅供参考，基于[Apache License][]授权，可以做一切用途，但不对代码所造成的影响负责，请慎重使用

##Guide

目前采用`maven`进行构建，日后更改为`gradle`，敬请期待

1. [modules][]目录包含示例所需功能模块和jar包依赖等

2. [demo][]为该项目实例演示且唯一，实现shiro权限管理和基础文档管理，界面部分为网络模版,逐步以[JQuery][]+[Bootstrap][]替代

3. 均在`idea`下开发，若出现`eclipse`等不兼容，请及时告知，或直接[pull request][]作出调整

>Announce:部分代码为引用他人，因遗漏或未知作者信息或涉及侵权行为请及时告知予以处理

##Task

- [X] 搭建基础框架，采用 Spring mvc + JPA，为方便演示，h2 mem + maven assembly，[release][]版本提供独立运行包下载

- [ ] 周期性重构和完善文档

- [ ] 搜集新的东西进行集成扩展

- [ ] CAS + shiro 做 SSO

- [ ] 集成[fixflow][]，`CMS`

>Announce:如有好的建议请提交[issue][]

##Join

欢迎提供[issue][] & [pull request][]，有不当之处，希望能及时指出与交流

代码规范请参考[Spring Framework Code Style][]

>Announce:需要注意的是**`sourcefile`统一使用`utf-8`编码**，测试类均以`Test`结尾


##License

The journal is released under version 2.0 of the [Apache License][].


[Journal]:http://oakhole.com/journal
[demo]:https://github.com/oakhole/journal/tree/master/demo
[modules]:https://github.com/oakhole/journal/tree/master/modules
[issue]:https://github.com/oakhole/journal/issues
[pull request]:https://github.com/oakhole/journal/pulls
[release]:https://github.com/oakhole/journal/releases
[fixflow]:https://github.com/fixteam/fixflow
[JQuery]:https://github.com/jquery/jquery
[Bootstrap]:https://github.com/twbs/bootstrap
[Spring Framework Code Style]:https://github.com/spring-projects/spring-framework/wiki/Spring-Framework-Code-Style
[Apache License]:http://www.apache.org/licenses/LICENSE-2.0
