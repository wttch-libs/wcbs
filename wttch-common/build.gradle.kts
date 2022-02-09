import com.wttch.plugin.libs.exts.wttchLibrary

group = "com.wttch"
version = "0.2.1.0005-SNAPSHOT"

wttchLibrary {
    name = "Wttch Common"
    description = "基本类库"
    githubOwner = "wttch-libs"
    githubProjectName = "wcbs"

    implementation("cglib:cglib:3.3.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}