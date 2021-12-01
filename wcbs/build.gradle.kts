import com.wttch.plugin.libs.dependencies.Spring

dependencies {
    api(project(":mybatis-common-query"))
    api(project(":web"))
    api(Spring.Boot.starter)
}