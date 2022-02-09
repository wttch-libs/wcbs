import com.wttch.plugin.libs.dependencies.Mysql
import com.wttch.plugin.libs.dependencies.Spring
import com.wttch.plugin.libs.exts.wttchLibrary
import com.wttch.plugin.libs.exts.wttchProject


group = rootProject.ext["wcbsGroup"]!!
version = rootProject.ext["wcbsVersion"]!!

wttchProject {
    name = "Wcbs Web"
    description = ""
    githubOwner = "wttch-libs"
    githubProjectName = "wcbs"
    isLibrary = false


    implementation(Spring.Boot.starter)
    implementation(Mysql.connector)
    // implementation(Spring.Boot.starterWeb)

    implementation(com.wttch.plugin.libs.dependencies.Alibaba.druid)
    implementation(project(":wcbs"))
    implementation(project(":multi-datasource"))
    implementation(com.wttch.plugin.libs.dependencies.Mybatis.spring)
    // implementation("com.wttch.wcbs:multi-datasource:0.0.2")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}