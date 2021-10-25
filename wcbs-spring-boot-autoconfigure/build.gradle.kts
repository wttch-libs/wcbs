import com.wttch.plugin.libs.dependencies.Spring


dependencies {
    implementation(Spring.Boot.starter)
    implementation(Spring.jdbc)

    compileOnly(Spring.Boot.configurationProcessor)
    annotationProcessor(Spring.Boot.configurationProcessor)
}
