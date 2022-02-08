# wcbs

(Wttch Common Base System) 随手搞的一个小的通用框架。为了方便以后的springBoot Web应用的开发，将一些常用的功能提取出来做成易用的框架。

* [多数据源](./multi-datasource/README.MD): 多数据源声明和切换
* [通用查询](./mybatis-common-query/README.MD): 简化你的api查询
* [mybaits自动配置](./mybatis-autoconfigure/README.MD): 替代mybatis autoconfigure 并做增强

# CI

现在是混合多个类库和iam在该项目中，方便类库的孵化。 CI 根据不同的分支进行，主要是wcbs和libs的CI，因为设计到自动化发布类库。这两个模块尽量独立编写build.gradle和CI，方便到时候拆分。