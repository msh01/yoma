#!/bin/bash
# 本脚本在服务器启动时执行
#1、赋予脚本可执行权限（/opt/script/autostart.sh是你的脚本路径）
# chmod +x /opt/script/autostart.sh
#2、vim /etc/rc.local ，在末尾增加如下内容
#su - user -c '/software/autoShell/severStartUp.sh'
#3、在centos7中，/etc/rc.d/rc.local的权限被降低了，所以需要执行如下命令赋予其可执行权限
#chmod +x /etc/rc.d/rc.local
/usr/bin/fdfs_trackerd  /etc/fdfs/tracker.conf  start
/usr/bin/fdfs_storaged /etc/fdfs/storage.conf  start
/usr/local/nginx/sbin/nginx
# docker 容器会开机自启，所以无需在这里设置

