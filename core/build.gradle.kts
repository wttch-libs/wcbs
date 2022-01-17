import com.wttch.plugin.libs.dependencies.JakartaAnnotation
import com.wttch.plugin.libs.dependencies.Jetbrains
import com.wttch.plugin.libs.dependencies.Spring

dependencies {
    api(Jetbrains.annotations)
    api(Spring.Boot.starter)

    api("com.wttch:common:0.2.1.0004-SNAPSHOT")


    implementation(JakartaAnnotation.api)

    implementation(Spring.beans)
    implementation(Spring.tx)
}
