plugins {
    id("com.android.library")
    kotlin("android")
    id("ktlint-plugin")
}

android {
    compileSdkVersion(Versions.android.compileSdk)
    buildToolsVersion(Versions.android.buildTools)

    defaultConfig {
        minSdkVersion(Versions.android.minSdk)
        targetSdkVersion(Versions.android.targetSdk)
    }
}

dependencies {
    api(project(":tanto"))

    compileOnly(Deps.dagger)

    compileOnly(Deps.androidx.fragment)
}
