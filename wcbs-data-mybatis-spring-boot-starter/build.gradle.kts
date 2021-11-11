import com.wttch.plugin.libs.dependencies.Spring

dependencies {
    api(project(":wcbs-core"))
    api(project(":wcbs-data-mybatis"))
    api(com.wttch.plugin.libs.dependencies.Mybatis.springBoot) {
        excludeSpringJdbc()
    }
    api(Spring.jdbc)
    api(Spring.Boot.starterJdbc)
    api(Spring.Boot.starterAop)

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}
