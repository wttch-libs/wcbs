import com.wttch.plugin.libs.dependencies.Spring
import com.wttch.plugin.libs.exts.wttchLibrary

plugins{
    java
    `java-library`
}

wttchLibrary("Wttch Common Library", "Wttch Common Library", "wttch-libs", "wcbs")
dependencies {
    api(project(":mybatis-common-query"))
    api(project(":multi-datasource"))
    api(project(":mybatis-autoconfigure"))
    api(project(":web"))
    api(project(":logs"))
    api(Spring.Boot.starter)
}