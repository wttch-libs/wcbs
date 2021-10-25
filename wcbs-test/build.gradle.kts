import com.wttch.plugin.libs.dependencies.Mybatis
import com.wttch.plugin.libs.dependencies.Mysql
import com.wttch.plugin.libs.dependencies.Spring

plugins {
    java
}

group = "com.wttch.wcbs"

repositories {
    mavenCentral()
}

dependencies {
    implementation(Spring.Boot.starter)
    implementation(Mybatis.springBoot)
    implementation(Mysql.connector)

    implementation(project(":wcbs-spring-boot-starter"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}