@ECHO OFF

:: %HOMEDRIVE%  C:
:: %HOMEPATH%  \Users\Ruben
:: %system32% ??
:: No spaces in paths
:: Program Files > ProgramFiles
:: cls  clear screen
:: CMD reads the system environment variables when it starts. To re-read those variables you need to restart CMD
:: Use console 2 http://sourceforge.net/projects/console/


:: Assign all Path variables
REM setx PHP="%HOMEDRIVE%\wamp\bin\php\php5.4.16"
REM setx SYSTEM32=";%HOMEDRIVE%\Windows\System32"
REM setx ANT=";%HOMEDRIVE%%HOMEPATH%\Downloads\apache-ant-1.9.0-bin\apache-ant-1.9.0\bin"
REM setx GRADLE=";%HOMEDRIVE%\tools\gradle-1.6\bin;"
REM setx ADT=";%HOMEDRIVE%\tools\adt-bundle-windows-x86-20130219\eclipse\jre\bin"
REM setx ADTTOOLS=";%HOMEDRIVE%\tools\adt-bundle-windows-x86-20130219\sdk\tools"
REM setx ADTP=";%HOMEDRIVE%\tools\adt-bundle-windows-x86-20130219\sdk\platform-tools"
REM setx YII=";%HOMEDRIVE%\wamp\www\yii\framework"
REM setx NODEJS=";%HOMEDRIVE%\ProgramFiles\nodejs"
REM setx CURL=";%HOMEDRIVE%\tools\curl_734_0_ssl"
REM setx COMPOSER=";%HOMEDRIVE%\ProgramData\Composersetxup\bin"
REM setx GIT=";%HOMEDRIVE%\Program Files\Git\cmd"

REM :: setx Path variable
REM setx PATH "%PHP%%SYSTEM32%%NODEJS%%COMPOSER%%YII%%GIT%" /m

REM :: setx Java variable
REM setx JAVA_HOME "%HOMEDRIVE%\ProgramFiles\Java\jdk1.7.0_21" /m

REM 设置java服务所引用到的环境变量，这些环境变量可以在java服务的配置文件中引用。至于为什么不写死在配置文件中，目的是问了避免把敏感的数据库连接配置提交到Git仓库中，造成泄密
REM 注意！！！：有的时候，明明环境变量已经设置成功了，但是java服务就是无法访问到。
REM 不知道是IDEA的bug还是JVM的bug，此时重启下IDEA所有窗口即可
setx YOMA_MYSQL_HOST  "127.0.0.1"`
setx YOMA_MYSQL_PORT 3307
setx YOMA_MYSQL_DB_NAME "yoma"
setx YOMA_MYSQL_USER "root"
setx YOMA_MYSQL_PASSWORD "Xbkj2019"
setx YOMA_REDIS_HOST "127.0.0.1"
setx YOMA_REDIS_PORT  6389
setx YOMA_REDIS_PASSWORD "ytjGqmB49z38"
setx YOMA_Tracker0 "127.0.0.1:22122"

PAUSE