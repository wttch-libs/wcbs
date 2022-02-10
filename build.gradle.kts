import com.wttch.plugin.libs.Constants

repositories {
    mavenCentral()
    maven {
        setUrl(Constants.MAVEN_SNAPSHOT_URL)
    }
}

ext["wcbsGroup"] = "com.wttch.wcbs"
ext["wcbsVersion"] = "0.0.3.0002-SNAPSHOT"

buildscript {
    dependencies {
        classpath("com.wttch.plugin:libs:1.0.4.08-SNAPSHOT")
    }

    repositories {
        mavenCentral()
        maven { setUrl("https://oss.sonatype.org/content/repositories/snapshots/") }
    }
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
    dependsOn(":logs:publish")
    dependsOn(":wcbs:publish")
    doLast {
        println("快照发布完成")
    }
}