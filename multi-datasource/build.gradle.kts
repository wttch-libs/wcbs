import com.wttch.plugin.libs.dependencies.*
import com.wttch.plugin.libs.exts.wttchLibrary

plugins{
    java
    `java-library`
}

wttchLibrary("Wttch Common Library", "Wttch Common Library", "wttch-libs", "wcbs")

dependencies {
    api(project(":core"))
    api(Spring.Boot.starterJdbc)
    api(Spring.Boot.starterAop)

    compileOnly(Spring.Boot.configurationProcessor)
    annotationProcessor(Spring.Boot.configurationProcessor)

    // 依赖两个数据库连接池，如果配置了但是使用的时候没有依赖就会忽略数据库连接池的配置
    compileOnly(Alibaba.druid)
    compileOnly(HikariCP.hikariCP)
}