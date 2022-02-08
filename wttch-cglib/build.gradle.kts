import com.wttch.plugin.libs.exts.wttchLibrary

plugins {
    java
}

group = "com.wttch"
version = "0.2.1.0005-SNAPSHOT"

wttchLibrary("Wttch Common Library", "Wttch Common Library", "wttch-libs", "wcbs")

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}