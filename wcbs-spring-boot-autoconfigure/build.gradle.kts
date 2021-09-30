import com.wttch.plugin.libs.Libs

plugins {
    java
}

group = "com.wttch"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(Libs.SpringBoot.starter)
    implementation(Libs.Spring.jdbc)

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}