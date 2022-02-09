import com.wttch.plugin.libs.dependencies.JakartaAnnotation
import com.wttch.plugin.libs.dependencies.Jetbrains
import com.wttch.plugin.libs.dependencies.Spring
import com.wttch.plugin.libs.exts.wttchProject

group = rootProject.ext["wcbsGroup"]!!
version = rootProject.ext["wcbsVersion"]!!

wttchProject {
    name = "Wcbs Core"
    description = "核心"
    githubOwner = "wttch-libs"
    githubProjectName = "wcbs"


    api(Jetbrains.annotations)
    api(Spring.Boot.starter)

    api(project(":common"))


    implementation(JakartaAnnotation.api)

    implementation(Spring.beans)
    implementation(Spring.tx)

    implementation("cglib:cglib:3.3.0")
}
