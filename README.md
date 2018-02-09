# TalentedRecruit

## 框架
* 前端Vue
* 后端SpringBoot

## 项目搭建
主要参考博文[《使用Gradle整合SpringBoot+Vue.js-开发调试与打包》](https://segmentfault.com/a/1190000008968295#articleHeader4)搭建项目
目录结构：

    -- TalentedRecruit
    |
    |-- server
    |   |
    |   |-- src            // 源码
    |   |-- build          // 构建输出目录
    |
    |-- web
    |   |
    |   |-- build-scripts  // vue 构建脚本
    |   |-- config         // vue 配置文件
    |   |-- src            // 源码
    |   |-- static         // 静态资源
    |   |-- build          // 构建输出目录

### 项目配置
* clone项目到本地
* 导入Gradle项目：[《IntelliJ IDEA如何导入Gradle项目》](http://blog.csdn.net/wangdong5678999/article/details/70255451)
* 在IDEA右侧找到Gradle的栏目，点击Refresh All Gradle Projects,下载依赖的jar
* 运行```TalentedRecruit/server```子项目的bootRun任务，启动Spring Boot，由于server模块的gradle配置中添加了web模块的依赖，启动时会先将web子项目打成jar包，web子项目的jar包中已经包含了webpack输出的静态资源。
* 浏览器打开[localhost:8080](localhost:8080)，看到Vue初始页面即环境配置正确

### 后端调试
* 直接run ```server > src > main > java > com.tr > Application.java```中的main函数
* 控制台输出```com.tr.Application: Started Application in XXX seconds```表示Spring Boot启动成功

#### RESTful API示例
[官方文档](https://spring.io/guides/gs/rest-service/)

示例：```server > src > main > java > com.tr > Controller > ExampleController.java```

后台Spring Boot启动后访问[localhost:8080/api/example](localhost:8080/api/example)，结果如下：

    {
        result: 1,
        msg: "hello!",
        description: "RESTful example"
    }
