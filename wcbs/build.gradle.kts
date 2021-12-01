import com.wttch.plugin.libs.dependencies.Spring

dependencies {
    api(project(":data:mybatis-common-query"))
    api(project(":web"))
    api(Spring.Boot.starter)

}