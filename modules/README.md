Modules
==

* 参考[!@springside](https://github.com/springside/springside4)的目录结构，如有需求，适当作出调整

* 封装所有模块，当运行`demo`时需要事先`mvn clean install`

* 根据具体事后需求而定，目前有`core`、`extension`、`support`，日后会进行重构，功能上尽量细分

###Task List

- [ ] `core`模块用来封装重构，例如：`BaseEntity`,`BaseDao`,`BaseService` and etc.

- [ ] `extension`模块用来封装扩展插件，例如：`mail`,`jmx`,`jms` and etc.

- [ ] `support`模块引用@springside的`support`，各类开发、集成等辅助插件，例如：h2 webconsole
