import com.wttch.plugin.libs.Constants
import com.wttch.plugin.libs.dependencies.Lombok
import com.wttch.plugin.libs.exts.isReleaseVersion
import com.wttch.plugin.libs.publish.pom
import com.wttch.plugin.libs.publish.sonatypeAutoRepositories

plugins {
    java
    `java-library`
    id("com.diffplug.spotless") version "5.11.0"
    `maven-publish`
    signing
}

repositories {
    mavenCentral()
    maven {
        setUrl(Constants.MAVEN_SNAPSHOT_URL)
    }
}

buildscript {
    dependencies {
        classpath("com.wttch.plugin:libs:1.0.4.03-SNAPSHOT")
    }

    repositories {
        mavenCentral()
        maven { setUrl("https://oss.sonatype.org/content/repositories/snapshots/") }
    }
}

subprojects {
    apply{
        plugin("java")
        plugin("com.diffplug.spotless")
    }

    dependencies {
        implementation("org.slf4j:slf4j-api:1.7.32")
    }


    spotless {
        java {
            // targetExclude("**/cn/techrecycle/rms/dao/entity/**/*.*")
            googleJavaFormat()
            // optional: you can specify a specific version and/or switch to AOSP style
            // googleJavaFormat('1.7').aosp()
        }
    }

    group = "com.wttch.wcbs"
    version = "0.0.3.0002-SNAPSHOT"
}

tasks.register("publishAll") {
    group = "publishing"
    doFirst {
        println("开始发布快照")
    }
    dependsOn(":core:publish")
    dependsOn(":multi-datasource:publish")
    dependsOn(":mybatis-common-query:publish")
    dependsOn(":mybatis-autoconfigure:publish")
    dependsOn(":web:publish")
    dependsOn(":wcbs:publish")
    doLast {
        println("快照发布完成")
    }
}