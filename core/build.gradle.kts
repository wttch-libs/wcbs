import com.wttch.plugin.libs.dependencies.JakartaAnnotation
import com.wttch.plugin.libs.dependencies.Jetbrains
import com.wttch.plugin.libs.dependencies.Spring
import com.wttch.plugin.libs.exts.wttchLibrary

plugins{
    java
    `java-library`
}

wttchLibrary("Wttch Common Library", "Wttch Common Library", "wttch-libs", "wcbs")

dependencies {
    api(Jetbrains.annotations)
    api(Spring.Boot.starter)

    api(project(":wttch-common"))


    implementation(JakartaAnnotation.api)

    implementation(Spring.beans)
    implementation(Spring.tx)

    implementation("cglib:cglib:3.3.0")
}
