import com.wttch.plugin.libs.dependencies.Mybatis
import com.wttch.plugin.libs.dependencies.Spring
import com.wttch.plugin.libs.exts.wttchProject

group = rootProject.ext["wcbsGroup"]!!
version = rootProject.ext["wcbsVersion"]!!

wttchProject {
    name = "Wcbs Mybatis AutoConfigure"
    description = "mybatis自动配置包的扩展，添加一些额外的自动配置功能"
    githubOwner = "wttch-libs"
    githubProjectName = "wcbs"

    api(Mybatis.mybatis)
    api(Mybatis.spring)
    api(Spring.Boot.autoconfigure)

    compileOnly(Spring.Boot.configurationProcessor)
    annotationProcessor(Spring.Boot.configurationProcessor)
    implementation(com.wttch.plugin.libs.dependencies.Slf4j.api)
}
