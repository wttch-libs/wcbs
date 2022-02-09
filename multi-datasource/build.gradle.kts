import com.wttch.plugin.libs.dependencies.Alibaba
import com.wttch.plugin.libs.dependencies.HikariCP
import com.wttch.plugin.libs.dependencies.Spring
import com.wttch.plugin.libs.exts.wttchProject

group = rootProject.ext["wcbsGroup"]!!
version = rootProject.ext["wcbsVersion"]!!

wttchProject {
    name = "Wcbs Multi DataSource"
    description = "多数据源和多数据源的切换"
    githubOwner = "wttch-libs"
    githubProjectName = "wcbs"


    api(project(":core"))
    api(Spring.Boot.starterJdbc)
    api(Spring.Boot.starterAop)

    compileOnly(Spring.Boot.configurationProcessor)
    annotationProcessor(Spring.Boot.configurationProcessor)

    // 依赖两个数据库连接池，如果配置了但是使用的时候没有依赖就会忽略数据库连接池的配置
    compileOnly(Alibaba.druid)
    compileOnly(HikariCP.hikariCP)
}