import com.wttch.plugin.libs.dependencies.Spring

dependencies {
    api(project(":wcbs-core"))

    api(Spring.Boot.starterWeb)

    compileOnly(Spring.Boot.configurationProcessor)
    annotationProcessor(Spring.Boot.configurationProcessor)
}
