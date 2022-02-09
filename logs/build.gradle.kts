import com.wttch.plugin.libs.dependencies.Jackson
import com.wttch.plugin.libs.dependencies.JakartaAnnotation
import com.wttch.plugin.libs.dependencies.Jetbrains
import com.wttch.plugin.libs.dependencies.Spring
import com.wttch.plugin.libs.exts.wttchProject

group = rootProject.ext["wcbsGroup"]!!
version = rootProject.ext["wcbsVersion"]!!

wttchProject {
    name = "Wcbs Log"
    description = "日志框架，使用注解和混入织入日志"
    githubOwner = "wttch-libs"
    githubProjectName = "wcbs"

    api(Jetbrains.annotations)
    api(Spring.Boot.starter)

    api(project(":core"))

    implementation(project(":common"))

    implementation("cglib:cglib:3.3.0")

    implementation(JakartaAnnotation.api)
    implementation(Jackson.databind)

    implementation(Spring.beans)
    implementation(Spring.Boot.starterAop)
}
