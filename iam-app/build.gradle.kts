import com.wttch.plugin.libs.dependencies.Mybatis
import com.wttch.plugin.libs.dependencies.Spring
import com.wttch.plugin.libs.exts.wttchLibrary

plugins {
    java

    id("org.springframework.boot") version "2.5.2"
}

group = "com.wttch.cloud"
version = "0.0.1.0001-SNAPSHOT"

wttchLibrary("Wttch Common Library", "Wttch Common Library", "wttch-libs", "wcbs")

dependencies {
    implementation(Spring.Boot.starter)
    implementation(Mybatis.springBoot)
    implementation(Spring.Boot.starterWeb)

    implementation("mysql:mysql-connector-java:8.0.25")

    implementation(project(":wcbs"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}