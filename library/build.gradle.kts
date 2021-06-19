plugins {
    id("com.android.library")
    kotlin("android")
    id("ktlint-plugin")
}

val tantoGroup = findProperty("group") as String
val tantoVersion = findProperty("version") as String

version = tantoGroup
group = tantoVersion

android {
    compileSdkVersion(Versions.android.compileSdk)
    buildToolsVersion(Versions.android.buildTools)

    defaultConfig {
        minSdkVersion(15)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    libraryVariants.all {
        generateBuildConfigProvider.configure { enabled = false }
    }

    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += "-Xexplicit-api=strict"
    }
}

dependencies {
    compileOnly(Deps.dagger)

    compileOnly(Deps.androidx.lifecycleViewModel)
    compileOnly(Deps.androidx.fragment)
}
