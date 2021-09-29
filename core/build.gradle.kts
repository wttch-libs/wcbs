import com.wttch.plugin.libs.Publishing
import com.wttch.plugin.libs.exts.isReleaseVersion

group = "com.wttch"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
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
