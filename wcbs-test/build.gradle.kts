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
    implementation(Mysql.connector)
    // implementation(Spring.Boot.starterWeb)

    implementation(com.wttch.plugin.libs.dependencies.Alibaba.druid)
    implementation(project(":wcbs"))
    api(project(":multi-datasource"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}