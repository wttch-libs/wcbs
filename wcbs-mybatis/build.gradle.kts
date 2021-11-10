import com.wttch.plugin.libs.dependencies.Mybatis
import com.wttch.plugin.libs.dependencies.Alibaba
import com.wttch.plugin.libs.dependencies.Spring

dependencies {
    implementation(Mybatis.springBoot) {
        excludeTxAndJdbc()
    }
    implementation("org.springframework.boot:spring-boot-starter-aop:2.5.2")
    implementation(project(":wcbs-core"))
    compileOnly(Spring.Boot.configurationProcessor)
    annotationProcessor(Spring.Boot.configurationProcessor)
    compileOnly(Alibaba.druid)
    compileOnly(Spring.Boot.starterJdbc)
    compileOnly(Spring.Boot.starterWeb)
}