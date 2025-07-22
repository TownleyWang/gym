java -jar -Xms256m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m ruoyi-admin.jar

nohup

// nohup java -jar ruoyi-admin.jar > output.log 2>&1 &
  disown
