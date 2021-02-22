#!/usr/bin/env bash
# 今天刚发现，linux crontab 的cron表达式只能精确到分钟，spring 里面的cron表达式可以精确到秒。。。。这也意味着，同样的表达式，含义不一样
# 这个东西坑了我一上午
# 定时任务  crontab -e    */10 * * * * sh /software/autoShell/autoRestartApp.sh
#app_test=$(netstat -an | grep ":8282" | awk '$1 == "tcp" && $NF == "LISTEN" {print $0}' | wc -l)
#if [ $app_test -eq 0 ]; then
#  echo $(date +%T%n%F) "服务异常关闭，需要重新启动" >>/software/logs/shell/test.start.log
#  nohup java -jar /software/apps/test/yoma-boot-0.0.1-SNAPSHOT.jar --spring.profiles.active=test >/software/logs/test/yoma-boot.log 2>&1 &
#else
#  echo $(date +%T%n%F) "测试环境的应用运行正常，无需重启!" >>/software/logs/shell/test.start.log
#fi
#app_prod=$(netstat -an | grep ":9292" | awk '$1 == "tcp" && $NF == "LISTEN" {print $0}' | wc -l)
#if [ $app_prod -eq 0 ]; then
#  echo $(date +%T%n%F) "服务异常关闭，需要重新启动" >>/software/logs/shell/prod.start.log
#  nohup java -jar /software/apps/test/yoma-boot-0.0.1-SNAPSHOT.jar --spring.profiles.active=test >/software/logs/test/yoma-boot.log 2>&1 &
#else
#  echo $(date +%T%n%F) "生产环境的应用运行正常，无需重启!" >>/software/logs/shell/prod.start.log
#fi
