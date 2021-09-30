import com.wttch.plugin.libs.Libs
import com.wttch.plugin.libs.Publishing
import com.wttch.plugin.libs.exts.isReleaseVersion

dependencies {
    implementation(Libs.Jetbrains.annotations)

    implementation("com.wttch:common:0.1-SNAPSHOT")

    implementation(Libs.Jakarta.annotation)
    implementation(Libs.Spring.beans)
    implementation(Libs.Spring.tx)
}
