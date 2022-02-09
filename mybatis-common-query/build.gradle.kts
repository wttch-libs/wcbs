import com.wttch.plugin.libs.dependencies.Jackson
import com.wttch.plugin.libs.dependencies.Mybatis
import com.wttch.plugin.libs.dependencies.Spring
import com.wttch.plugin.libs.exts.wttchProject

group = rootProject.ext["wcbsGroup"]!!
version = rootProject.ext["wcbsVersion"]!!

wttchProject {
    name = "Wcbs Mybatis Common Query"
    description = "通用查询"
    githubOwner = "wttch-libs"
    githubProjectName = "wcbs"


    api(project(":core"))

   // api(project(":mybatis-autoconfigure"))
//    api(project(":data:multi-datasource"))
    api("org.mybatis:mybatis-typehandlers-jsr310:1.0.2")
    api(Mybatis.mybatis)
    api(Spring.Boot.starterAop)
    implementation(Jackson.annotations)
}
