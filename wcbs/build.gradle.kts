import com.wttch.plugin.libs.dependencies.Spring

dependencies {
    api(project(":data:data-mybatis"))
    api(project(":web"))
    api(Spring.Boot.starter)
}