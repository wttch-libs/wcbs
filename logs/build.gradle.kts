import com.wttch.plugin.libs.dependencies.JakartaAnnotation
import com.wttch.plugin.libs.dependencies.Jetbrains
import com.wttch.plugin.libs.dependencies.Spring
import com.wttch.plugin.libs.dependencies.Jackson

dependencies {
    api(Jetbrains.annotations)
    api(Spring.Boot.starter)

    api(project(":core"))

    implementation("com.wttch:common:0.1-SNAPSHOT")

    implementation(JakartaAnnotation.api)
    implementation(Jackson.databind)

    implementation(Spring.beans)
    implementation(Spring.Boot.starterAop)
}
