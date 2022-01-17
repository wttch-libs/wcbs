import com.wttch.plugin.libs.dependencies.Spring

dependencies {
    api(project(":mybatis-common-query"))
    api(project(":multi-datasource"))
    api(project(":mybatis-autoconfigure"))
    api(project(":web"))
    api(project(":logs"))
    api(Spring.Boot.starter)
}