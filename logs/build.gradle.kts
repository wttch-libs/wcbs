import com.wttch.plugin.libs.dependencies.JakartaAnnotation
import com.wttch.plugin.libs.dependencies.Jetbrains
import com.wttch.plugin.libs.dependencies.Spring
import com.wttch.plugin.libs.dependencies.Jackson
import com.wttch.plugin.libs.exts.wttchLibrary

plugins{
    java
    `java-library`
}

wttchLibrary("Wttch Common Library", "Wttch Common Library", "wttch-libs", "wcbs")

dependencies {
    api(Jetbrains.annotations)
    api(Spring.Boot.starter)

    api(project(":core"))

    implementation(project(":wttch-common"))

    implementation("cglib:cglib:3.3.0")

    implementation(JakartaAnnotation.api)
    implementation(Jackson.databind)

    implementation(Spring.beans)
    implementation(Spring.Boot.starterAop)
}
