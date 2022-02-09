import com.wttch.plugin.libs.dependencies.Mybatis
import com.wttch.plugin.libs.dependencies.Spring
import com.wttch.plugin.libs.exts.wttchProject


wttchProject {
    name = "IAM"
    description = "IAM"
    githubOwner = "wttch-libs"
    githubProjectName = "wcbs"
    isLibrary = false

    implementation(Spring.Boot.starter)
    implementation(Mybatis.springBoot)
    implementation(Spring.Boot.starterWeb)

    implementation("mysql:mysql-connector-java:8.0.25")

    implementation(project(":wcbs"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}