# 多数据源

帮助你可以声明多个数据源，并可以通过dao上的注解在各个数据源中随意切换

引入依赖

```kotlin
// 添加中央仓库
repositories {
    mavenCentral()
}
```

```kotlin
dependencies {
    implementation("com.wttch.wcbs:multi-datasource:0.0.3")
}
```

## 1. 多数据源声明

框架可以定义 db0-db9 十个数据源, 同时可以为数据源配置数据库连接池 hikari/druid，具体参数如下：

```yaml
wcbs:
  data:
    database:
      # 默认数据源的名字，db0-db9 中某个数据源的name
      primary: iam
      db0:
        # 数据源name
        name: iam
        # 数据源
        url: jdbc:mysql://host:3306/database
        # 数据源用户名
        username: username
        # 数据源密码
        password: password
        # 数据库驱动名称
        driver-class-name: com.mysql.cj.jdbc.Driver
        # hikari 数据库连接池，内部配置字段和 hikari 自动化配置类同
        hikari:
          auto-commit: true
          max-lifetime: 60000
        # 是否加载默认的函数，框架会帮助生成一些工具函数，只针对当前数据源起作用
        load-function: true
        # 初始化sql的路径，只针对当前数据源起作用
        init-sql-path: classpath:/init/*.sql
      db1:
        # 数据源name
        name: iam2
        # 数据源
        url: jdbc:mysql://host:3306/database
        # 数据源用户名
        username: username
        # 数据源密码
        password: password
        # 数据库驱动名称
        driver-class-name: com.mysql.cj.jdbc.Driver
        # druid 相关的配置，如果配置并且项目中有druid依赖，将会启动druid连接池，否则无效
        druid:
          initial-size: 10
          filter:
            wall:
              config:
                multi-statement-allow: true
```

## 2. 多数据源切换

可以在mapper或者业务相关的函数或者类上添加 ```@AssignDataSource("datasourceName"")``` 注解, 注解参数为对应配置的多数据源的name， 在执行相关类或者函数时会自动切换到指定的数据源。

类上的注解要弱于函数上的数据源，即函数上的数据源转换会覆盖类上的。

例如

```java
// 类下所有函数会使用数据源 iam1
@AssignDataSourec("iam1")
public class TestMapper {

    // 数据源iam1
    public void test1();

    // 会覆盖类上的声明使用iam2数据源
    @AssignDataSourec("iam2")
    public void test2();
}

// 没有声明，则使用默认数据源，即 primary声明的iam1
public class TestMapper2 {
    public void test();
}

```