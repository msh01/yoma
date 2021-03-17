# YOMA 

[toc]

一个**小而美**的低代码全栈开发平台，一键生成后端api接口+前端页面代码+在线接口文档，节省50%的前后端开发的工作量，平台代码100%开源。平台适用于企业信息化、政务、中小型互联网等项目

平台采用前后端分离架构，基于如下流行的开源框架，易上手+便于后期维护



**后端：https://github.com/msh01/yoma**

spring boot + jwt +spring security + mybatis +swagger + spring  data redis + spring data mongo 

接入的SDK有：阿里云短信、极光推送、华为推送

**前端：https://github.com/msh01/yoma-web**

vue +element ui + axios+vuex + vue router 

**开发工具：**

  IDEA+Git+Navicat+XShell+Notepad++


## 快速开始

把项目源码克隆到本地，然后执行如下步骤

**数据库等中间件安装+数据库的初始化**

（*以下安装方式基于docker。当然了，如果你不擅长docker，也可以跳过下面的说明，采用传统方式安装，方法请自行百度*。）

- mysql+redis + rocketmq 等中间件的安装，基于docker compose 一个启动命令搞定； 

- 这些中间件可以安装在**本地**也可以安装在**服务器端**。安装前请确保你本地安装的有docker desktop或者服务器端安装的有docker compose

- 找到项目目录中的启动模板文件`docker-compose.yml`， 在此文件所在的目录执行命令（可以按需安装，模板文件中某些服务，比如 Gitlab、MongoDB、nexus、 如果用不到则可以注释掉）

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
  npm install 
  # npm如果安装报错的情况下可以尝试用yarn安装。yarn安装命令如下
  # npm install --global yarn
  # 通过yarn安装所有所需依赖
  # yarn install  
  ```


- 把前端项目导入到IDEA里面，找到`package.json` 文件中的`"dev": "vue-cli-service serve",`点击左侧的三角箭头，选择`debug dev`
  即可完成启动。启动过程中没有任何报错信息，并自动打开登录页，则代表启动成功，

- 后台登录的账户密码为 `admin`  / 1`23456`



## 生成的代码说明

- 针对某表，一般会生成如下六个后端代码文件（分别对应标准mvc架构中的controller、service、dao、mappe.xml、entity、queryDTO ）和两个前端代码文件（一个vue文件一个js文件）
- 注意：entity并不是jpa中标准意义上的entity，仅仅是用作接收页面增加、编辑的请求参数和返回查询结果集。queryDTO 用作接受列表查询的参数，比如`like、==、between`。
- 把生成的代码分别拷贝到对应的包下即可
- 小窍门：@AnonymousAccess注解加在某个controller内部某方法上代表开启此接口的匿名访问，不会被权限验证拦截。为了方便调试，可以在开发的时候加上，上线前移除
- 前端代码拷贝时，注意目录。因为配置菜单路由时会用到



## 后端部署

### 打包并把打好后jar包上传到服务器（可借助xftp或者winscp）

在后端项目的根目录执行 `mvn package` 命令，打好的jar包在`yoma-boot`模块的target目录下

上传到服务器的某个目录下，本人的jar包放置的绝对路径如下。后面的启动脚本里面的路径要和jar包的绝对路径一一对应

```shell
# 测试环境jar包的路径
/software/apps/test/yikong-boot-0.0.1-SNAPSHOT.jar
# 生产环境jar包的路径
/software/apps/prod/yikong-boot-0.0.1-SNAPSHOT.jar
```

### 通过自动化脚本进行启动、重启、开机自启

常用的启动、重启、开机自启等自动化脚本已经放在的**项目的autoshell目录**下。用工具把此脚本传输到服务器上，本人将自动化脚本放在了服务器的`/software/autoShell/` 目录下，不同的脚本，有不同的功能。

请用chmod + xx.sh 为以下脚本赋予执行权限

```shell
# 每间隔10分钟执行一次，检测到后端服务关闭掉则自动重启
autoRestartApp.sh
# 手动启动或者重启服务
restartApp.sh
# 开机自启脚本
severStartUp.sh
```

### 运行日志查看

```shell
# 测试环境查看实时日志 末尾的时间换成当前时间
tail -f /software/logs/test/yoma-boot/yoma-boot.2020-10-08.10.log
# 生产环境查看实时日志 末尾的时间换成当前时间
tail -f /software/logs/prod/yoma-boot/yoma-boot.2020-08-29.02.log
```

## 前端部署

### 修改env.production文件

修改其中的VUE_APP_BASE_API和VUE_APP_WS_API为生产环境中的实际地址

### 打包

```shell
npm run build:prod
```

打包完成后会在根目录生成 `dist` 文件夹，我们需要将他上传到服务器中

### Nginx 配置

在 `nginx/conf/nginx.conf` 添加配置

（默认History 模式的配置）

```bash
server
    {
        listen 80;
        server_name 域名/外网IP;
        index index.html;
        root  /software/frontend/yoma/dist;  #dist上传的路径
        # 避免访问出现 404 错误
        location / {
          try_files $uri $uri/ @router;
          index  index.html;
        }
        location @router {
          rewrite ^.*$ /index.html last;
        }  
    } 
```

最后记得重启nginx

## 开发计划

当前版本 **v1.00**

**V 1.0.0 完成以下目标：**

1. 自动生成前后端代码，实现低代码开发的目标。面向后台系统居多的企业信息化项目、政务项目、中小型互联网项目
2. 管理后台基本实现所有表的CRUD操作；
3. 后端服务能够对参数进行检验。
4. 文档优化（待完成）

**V 1.5.0 完成以下目标：**

- 系统数据库和反向生成的目标数据库区分开，更加灵活

**V 2.0.0 完成以下目标：**

1.  `flowable`工作流引擎的接入，并与项目原有的账户角色打通。（`flowable`衍生自`activiti`，和后者是同一个作者）
2.  基于此平台衍生出一个物联网平台的脚手架出来，涉及到mqtt、opcua等协议支持，多mqtt broker等

**v 3.0.0** 

推出微服务版本的`yoma-cloud`, 整合spring cloud+k8s 

## 进阶阅读

（以下文章对应的示例已经在项目中得到体现，文章待后续补充）

- [Intellij IDEA配置优化最佳实践](https://github.com/msh01/awesome_intellij_idea_config)
- 基于spring boot的maven 多模块的聚合继承
- maven依赖重复导入导致bean被实例化两次无法启动的问题排查
- spring boot整合swagger，并支持自动导出排版优美的PDF接口文档
- spring boot整合fastdfs
- 通过spring data mongo  来实现对mongodb 的复杂的分组聚合查询
- Java8 localDateTime在spring boot应用中的全局日期格式化
- spring boot应用对响应体进行全局封装，方便统一前后端交互的数据格式
- spring boot应用对全局异常做拦截并包装响应体（无论实际错误是什么，给前端返回的http状态码应该始终为200，而不是500,400之类的）
- 基于easypoi实现对复杂排版格式的excel的导出
- spring boot+spring security+jwt实现前后端认证和鉴权
- 基于@Aspect切面来实现对用户操作日志的自动记录
- idea调试vue应用或者react应用的技巧
- element ui的file-upload组件配合spring boot实现文件上传和下载
- spring boot+logback实现按日期生成日志文件+自动删除超过30天的+日志级别（以及为什么日志文件没有自动切割的问题）
- java表达式引擎的整合
- 基于责任链模式实现对复杂场景的处理
- rocketmq踩空总结：同一个java服务在不同环境的消费组名称必须保证不同，否则rocketmq会采用负载均衡策略进行消费，只有33%的概率会被当前服务消费成功
- spring boot整合极光推送
- spring boot整合华为推送

## 致谢

本项目基于或参考以下项目：

1.  [eladmin](https://github.com/elunez/eladmin)

   项目介绍：基于spring boot + spring data jpa + vue +element 的开发平台

   参考部分：代码生成逻辑和认证鉴权

2. [vue-element-admin](https://github.com/PanJiaChen/vue-element-admin)
  
   项目介绍： 一个基于Vue和Element的后台集成方案
  
   项目参考：布局和组建封装

3. [jeesite](https://gitee.com/thinkgem/jeesite4)

   项目介绍：基于（Spring Boot、Spring MVC、Apache Shiro、MyBatis、Beetl、Bootstrap、AdminLTE）的开发框架

   项目参考：优雅简练的基于泛型的增删改查的封装

- 


## 问题反馈

 * 开发者有问题或者好的建议可以用Issues反馈交流，请给出详细信息
 * 在开发交流群中应讨论开发、业务和合作问题
 * 如果真的需要微信群里提问，请在提问前先完成以下过程：
    * 请阅读[提问的智慧](https://github.com/ryanhanwu/How-To-Ask-Questions-The-Smart-Way/blob/master/README-zh_CN.md)；
    * 请百度或谷歌相关技术；
    * 请查看相关技术的官方文档，例如微信小程序的官方文档；
    * 请提问前尽可能做一些DEBUG或者思考分析，然后提问时给出详细的错误相关信息以及个人对问题的理解。

## License

 Apache License Version 2.0

