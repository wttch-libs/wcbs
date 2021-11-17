import com.wttch.plugin.libs.dependencies.Spring

dependencies {
    api(project(":wcbs-core"))

    api(project(":wcbs-mybatis-autoconfigure"))
    api(project(":wcbs-data-jdbc"))
    api(Spring.Boot.starterAop)

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}
