import com.wttch.plugin.libs.dependencies.Spring
import com.wttch.plugin.libs.exts.wttchLibrary

plugins{
    java
    `java-library`
}

wttchLibrary("Wttch Common Library", "Wttch Common Library", "wttch-libs", "wcbs")
dependencies {
    api(project(":core"))

    api(Spring.Boot.starterWeb)

    compileOnly(Spring.Boot.configurationProcessor)
    annotationProcessor(Spring.Boot.configurationProcessor)
}
