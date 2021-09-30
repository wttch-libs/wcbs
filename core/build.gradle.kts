import com.wttch.plugin.libs.Libs
import com.wttch.plugin.libs.Publishing
import com.wttch.plugin.libs.exts.isReleaseVersion

group = "com.wttch"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        setUrl(com.wttch.plugin.libs.Constants.MAVEN_SNAPSHOT_URL)
    }
}

dependencies {
    implementation(Libs.Jetbrains.annotations)

    implementation("com.wttch:common:0.1-SNAPSHOT")

    implementation(Libs.Jakarta.annotation)
    implementation(Libs.Spring.beans)
    implementation(Libs.Spring.tx)
}


publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "wcbs-core"

            println("ArtifactInfo($groupId:$artifactId:$version)")

            from(components["java"])

            pom(Publishing.pom)
        }
    }
    repositories(Publishing.repositories(project))
}

signing {
    // useInMemoryPgpKeys("", "")
    sign(publishing.publications["mavenJava"])
}

tasks.withType<Sign>().configureEach {
    onlyIf { project.isReleaseVersion() }
}
