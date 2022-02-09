import com.wttch.plugin.libs.dependencies.Spring
import com.wttch.plugin.libs.exts.wttchProject

group = rootProject.ext["wcbsGroup"]!!
version = rootProject.ext["wcbsVersion"]!!

wttchProject {
    name = "Wcbs Web"
    description = "框架web部分"
    githubOwner = "wttch-libs"
    githubProjectName = "wcbs"


    api(project(":core"))
    api(Spring.Boot.starterWeb)
    compileOnly(Spring.Boot.configurationProcessor)
    annotationProcessor(Spring.Boot.configurationProcessor)
}
