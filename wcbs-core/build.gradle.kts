import com.wttch.plugin.libs.dependencies.JakartaAnnotation
import com.wttch.plugin.libs.dependencies.Jetbrains
import com.wttch.plugin.libs.dependencies.Spring

dependencies {
    implementation(Jetbrains.annotations)

    implementation("com.wttch:common:0.1-SNAPSHOT")


    implementation(JakartaAnnotation.api)

    implementation(Spring.beans)
    implementation(Spring.tx)
}
