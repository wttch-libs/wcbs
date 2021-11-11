import com.wttch.plugin.libs.dependencies.*

dependencies {
    api(Mybatis.springBoot) {
        excludeSpringJdbc()
    }
    api(project(":wcbs-data-jdbc"))

    compileOnly(Spring.Boot.configurationProcessor)
    annotationProcessor(Spring.Boot.configurationProcessor)
    
    // 依赖两个数据库连接池，如果配置了但是使用的时候没有依赖就会忽略数据库连接池的配置
    compileOnly(Alibaba.druid)
    compileOnly(HikariCP.hikariCP)
}