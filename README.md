# YOMA 

一个**小而美**的低代码全栈开发平台，一键生成后端api接口+前端页面代码+在线接口文档，节省50%的前后端开发的工作量，平台代码100%开源。平台适用于企业信息化、政务、中小型互联网等项目

平台采用前后端分离架构，基于如下流行的开源框架，易上手+便于后期维护
**后端：**
spring boot + jwt +spring security + mybatis +swagger + spring  data redis + spring data mongo 

**前端：**

vue +element ui + axios+vuex + vue router 

**开发工具：**

 推荐 IDEA集成开发+Git版本管理+Navicat数据库连接+XShell服务器管理+nodepad++


## 快速开始

把项目源码克隆到本地，然后执行如下步骤

**数据库等中间件安装**+数据库的初始化

- mysql+redis + rocketmq 等中间件的安装，基于docker compose 一个启动命令搞定； 当然如果你不擅长docker，可以不用docker用传统方式安装也可以，方法请自行百度。

- 这些中间件可以安装在**本地**也可以安装在**服务器端**。安装前请确保你本地安装的有docker desktop或者服务器端安装的有docker compose

- 找到项目目录中的启动模板文件`docker-compose.yml`， 在此文件所在的目录执行命令（模板文件中某些服务，比如 Gitlab、MongoDB、nexus、 如果用不到则可以注释掉）

```shell
# 启动模板下中编排的所有docker服务。（本地不存在镜像则自动从远程拉取，过程可能较慢）
docker-compose up -d 
# -d 参数代表容器服务后台运行，避免窗口关闭后容器服务自动挂掉。如果你想看到启动的细节，则可把-d参数去掉
# 更多的docker compose命令，比如重启、移除、监控请参考https://www.runoob.com/docker/docker-compose.html
```

- 用Navicat连接上刚刚安装成功的MySQL，并创建名称为yoma的数据库。在此数据库下执行init.sql，完成数据库的初始化

**配置后端开发环境**。

- 后端项目打包编译基于jdk8+maven，两者可自动百度安装
- 把后端项目**yoma**导入到IDEA里面，IDEA会自动下载项目所需的依赖并自动构建，过程可能需要10分钟。另外为了避免报错，IDEA需要安装`lombok`插件
- mysql连接、redis连接等配置信息并没有直接写死在spring boot 的application.yml 等配置文件中，而是直接在application.yml 中读取本机系统环境变量。你问为什么要多此一举？当然是为了防止不小心把敏感信息提交到远程仓库，造成泄密啊！！！配置环境变量的步骤
  -  找到项目中的`setEnviromentvariableForYoma.bat`，里面把脚本中的账户密码连接信息改成你自己的
  - 用管理员权限运行此bat脚本，即可一步配置完成环境变量
  - 配置完成后记得把IDEA重启下，
- 找到`YomaBootApplication`入口类，点击图标，选择debug启动即可。如果启动过程中没有报出任何错误日志，则代表后端服务启动成功

**配置前端开发环境**。

- 前端项目编译打包基于npm 或者yarn. 请确保你本地安装的有node.js 。 

  ```shell
  #   设置npm淘宝镜像源 ，加快安装各种包的速度
  npm config set registry https://registry.npm.taobao.org
  ```

​     

- 在前端项目yoma-web的根目录执行如下命令，安装前端项目所依赖的各种包

  ```shell
  # 命令安装没有任何报错则代表安装成功
  npm install --global yarn
  # npm如果安装报错的情况下可以尝试yarn。yarn安装命令如下
  # npm install --global yarn
  # 放弃npm换用yarn进行安装
  # yarn install  
  ```

  

- 把前端项目导入到IDEA里面，找到`package.json` 文件中的`"dev": "vue-cli-service serve",`点击左侧的三角箭头，选择`debug dev`即可完成启动。启动过程中没有任何报错信息，并自动打开登录页，则代表启动成功，
- 后台登录的账户密码为admin  / 123456

## 开发计划

当前版本 **v1.00**

**V 1.0.0 完成以下目标：**

1. 自动生成前后端代码，实现低代码开发的目标。面向后台系统居多的企业信息化项目、政务项目、中小型互联网项目
2. 管理后台基本实现所有表的CRUD操作；
3. 后端服务能够对参数进行检验。
4. 文档优化（待完成）

**V 2.0.0 完成以下目标：**

1.  `flowable`工作流引擎的接入，并与项目原有的账户角色打通。（`flowable`衍生自`activiti`，和后者是同一个作者）

**v 3.0.0** 

推出微服务版本的`yoma-cloud`, 整合spring cloud+k8s 

## 致谢

本项目基于或参考以下项目：

1.  [eladmin](https://github.com/elunez/eladmin)

   项目介绍：基于spring boot + spring data jpa + vue +element 的开发平台

   参考部分：代码生成逻辑和前后端数据交互

2. [vue-element-admin](https://github.com/PanJiaChen/vue-element-admin)
  
   项目介绍： 一个基于Vue和Element的后台集成方案
  
   项目参考：布局和组建封装

3. [jeesite](https://gitee.com/thinkgem/jeesite4)

   项目介绍：基于（Spring Boot、Spring MVC、Apache Shiro、MyBatis、Beetl、Bootstrap、AdminLTE）的开发框架

   项目参考：优雅的增删改查的封装

## 进阶阅读




## 问题反馈

 * 开发者有问题或者好的建议可以用Issues反馈交流，请给出详细信息
 * 在开发交流群中应讨论开发、业务和合作问题
 * 如果真的需要微信群里提问，请在提问前先完成以下过程：
    * 请仔细阅读本项目文档，特别是是[**FAQ**](https://linlinjava.gitbook.io/litemall/faq)，查看能否解决；
    * 请阅读[提问的智慧](https://github.com/ryanhanwu/How-To-Ask-Questions-The-Smart-Way/blob/master/README-zh_CN.md)；
    * 请百度或谷歌相关技术；
    * 请查看相关技术的官方文档，例如微信小程序的官方文档；
    * 请提问前尽可能做一些DEBUG或者思考分析，然后提问时给出详细的错误相关信息以及个人对问题的理解。

## License

 