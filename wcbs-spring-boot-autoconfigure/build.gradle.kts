import com.wttch.plugin.libs.dependencies.*


dependencies {
    implementation(Spring.Boot.starter)
    implementation(Spring.jdbc)

    implementation(Jetbrains.annotations)

    api(project(":wcbs-core"))

    compileOnly(Spring.Boot.configurationProcessor)
    annotationProcessor(Spring.Boot.configurationProcessor)
}
