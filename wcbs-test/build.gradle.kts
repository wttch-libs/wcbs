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
    implementation(Mybatis.springBoot) {
        excludeTxAndJdbc()
    }
    implementation(Mysql.connector)
    implementation(Spring.Boot.starterWeb)
    implementation(project(":wcbs-web"))

    implementation(project(":wcbs-spring-boot-starter"))

    implementation(project(":wcbs-mybatis"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}