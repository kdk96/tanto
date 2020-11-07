plugins {
    `kotlin-dsl`
}

repositories {
    google()
    jcenter()
}

dependencies {
    implementation(Deps.androidGradlePlugin)
    implementation(Deps.kotlinGradlePlugin)
    implementation(Deps.bintrayGradlePlugin)
}

kotlin {
    // Add Deps to compilation, so it will become available in main project
    sourceSets.getByName("main").kotlin.srcDir("buildSrc/src/main/kotlin")
}

gradlePlugin {
    plugins.register("ktlint-plugin") {
        id = "ktlint-plugin"
        implementationClass = "ktlint.KtlintPlugin"
    }
    plugins.register("publish-plugin") {
        id = "publish-plugin"
        implementationClass = "publish.PublishPlugin"
    }
}
