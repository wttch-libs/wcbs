import com.wttch.plugin.libs.dependencies.Mysql
import com.wttch.plugin.libs.dependencies.Spring
import com.wttch.plugin.libs.exts.wttchLibrary

plugins {
    java
    `java-library`
}

group = "com.wttch.wcbs"

wttchLibrary("Wttch Common Library", "Wttch Common Library", "wttch-libs", "wcbs")

dependencies {
    implementation(Spring.Boot.starter)
    implementation(Mysql.connector)
    // implementation(Spring.Boot.starterWeb)

    implementation(com.wttch.plugin.libs.dependencies.Alibaba.druid)
    implementation(project(":wcbs"))
    api(project(":multi-datasource"))
    implementation(com.wttch.plugin.libs.dependencies.Mybatis.spring)
    // implementation("com.wttch.wcbs:multi-datasource:0.0.2")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}