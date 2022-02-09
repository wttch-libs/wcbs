import com.wttch.plugin.libs.dependencies.Spring
import com.wttch.plugin.libs.exts.wttchProject

group = rootProject.ext["wcbsGroup"]!!
version = rootProject.ext["wcbsVersion"]!!

wttchProject {
    name = "Wcbs"
    description = "框架整合模块，整合所有的模块到这个类库来"
    githubOwner = "wttch-libs"
    githubProjectName = "wcbs"


    api(project(":mybatis-common-query"))
    api(project(":multi-datasource"))
    api(project(":mybatis-autoconfigure"))
    api(project(":web"))
    api(project(":logs"))
    api(Spring.Boot.starter)
}