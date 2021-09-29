plugins {
    java
    id(Plugins.spotless) version Versions.Plugin.spotless
    `maven-publish`
    signing
}

repositories {
    mavenCentral()
    maven {
        setUrl("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

subprojects {
    apply {
        plugin(Plugins.spotless)
        plugin("java")
        plugin("maven-publish")
        plugin("signing")
    }

    dependencies {
        testImplementation("junit", "junit", "4.12")
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