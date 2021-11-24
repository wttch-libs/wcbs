import com.wttch.plugin.libs.dependencies.Spring

dependencies {
    api(project(":wcbs-data:wcbs-data-mybatis"))
    api(project(":wcbs-web"))
    api(Spring.Boot.starter)
}