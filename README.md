
## 项目部署

- 当前数据库使用内置mysql,修改application.properties数据库连接信息

- 运行elm2.0/sql.sql脚本构建数据库

- ORM 采用MyBatis 

## 项目启动
- 当前项目仅依赖 jdk(version>=17) 即可正常运行。
- 
- target/myapp-1.0.jar已经有可以运行的jar包
- 
- 直接运行即可Run: java -jar target/myapp-1.0.jar （windows上将/换成\）
- 
- 若jar包无法使用，重新打包mvn clean package
- 
- Run: java -jar target/myapp-1.0.jar （windows上将/换成\）


