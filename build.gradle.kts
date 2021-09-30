import com.wttch.plugin.libs.Constants
import com.wttch.plugin.libs.Libs

plugins {
    java
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
        classpath("com.wttch.plugin:libs:0.3-SNAPSHOT")
    }

    repositories {
        maven {
            setUrl("https://oss.sonatype.org/content/repositories/snapshots/")
        }
    }
}

subprojects {
    apply {
        plugin("com.diffplug.spotless")
        plugin("java")
        plugin("maven-publish")
        plugin("signing")
    }

    dependencies {
        testImplementation("junit", "junit", "4.12")

        // lombok
        compileOnly(Libs.lombok)
        annotationProcessor(Libs.lombok)
        testCompileOnly(Libs.lombok)
        testAnnotationProcessor(Libs.lombok)
    }

    spotless {
        java {
            // targetExclude("**/cn/techrecycle/rms/dao/entity/**/*.*")
            googleJavaFormat()
            // optional: you can specify a specific version and/or switch to AOSP style
            // googleJavaFormat('1.7').aosp()
        }
    }


    java {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
        withJavadocJar()
        withSourcesJar()
    }
}
