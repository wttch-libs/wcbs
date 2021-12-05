import com.wttch.plugin.libs.dependencies.Spring
import com.wttch.plugin.libs.dependencies.Jackson
import com.wttch.plugin.libs.dependencies.Mybatis

dependencies {
    api(project(":core"))

   // api(project(":mybatis-autoconfigure"))
//    api(project(":data:multi-datasource"))
    api("org.mybatis:mybatis-typehandlers-jsr310:1.0.1")
    api(Mybatis.mybatis)
    api(Spring.Boot.starterAop)
    implementation(Jackson.annotations)

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}
