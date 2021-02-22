# ====================================测试环境=============================================

# 杀死指定端口号的进程
fuser -k -n tcp 8282
# 后台启动应用并把日志输入到指定文件  测试环境
nohup java -jar /software/apps/test/yoma-boot-0.0.1-SNAPSHOT.jar --spring.profiles.active=test > /software/logs/test/yoma-boot.log 2>&1 &




# =======================================生产环境=============================================

# 杀死指定端口号的进程
fuser -k -n tcp 9292
# 后台启动应用并把日志输入到指定文件
nohup java -jar /software/apps/product/yoma-boot-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod> /software/logs/prod/yoma-boot.log 2>&1 &
