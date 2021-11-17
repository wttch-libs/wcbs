import com.wttch.plugin.libs.dependencies.Mybatis
import com.wttch.plugin.libs.dependencies.Spring

dependencies {
    api(Mybatis.mybatis)
    api(Mybatis.spring)
    api(Spring.Boot.autoconfigure)

    compileOnly(Spring.Boot.configurationProcessor)
    annotationProcessor(Spring.Boot.configurationProcessor)
}