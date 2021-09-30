import com.wttch.plugin.libs.Constants
import com.wttch.plugin.libs.Libs
import com.wttch.plugin.libs.exts.isReleaseVersion

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
        plugin("java")
        plugin("com.diffplug.spotless")
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

    repositories {
        mavenCentral()
        maven {
            setUrl(com.wttch.plugin.libs.Constants.MAVEN_SNAPSHOT_URL)
        }
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

    group = "com.wttch"
    version = "0.1-SNAPSHOT"

    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                artifactId = project.name

                from(components["java"])

                pom(com.wttch.plugin.libs.Publishing.pom)
            }
        }
        repositories(com.wttch.plugin.libs.Publishing.repositories(project))
    }

    tasks.named("publish") {
        doLast {
            println("快照已发布：(${project.group}:${project.name}:${project.version})")
        }
    }

    signing {
        // useInMemoryPgpKeys("", "")
        sign(publishing.publications["mavenJava"])
    }

    tasks.withType<Sign>().configureEach {
        onlyIf { project.isReleaseVersion() }
    }
}

tasks.register("publishAll") {
    group = "publishing"
    doFirst {
        println("开始发布快照")
    }
    dependsOn(":wcbs-core:publish")
    dependsOn(":wcbs-spring-boot-autoconfigure:publish")
    dependsOn(":wcbs-spring-boot-starter:publish")
    doLast {
        println("快照发布完成")
    }
}