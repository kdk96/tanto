plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("ktlint-plugin")
}

android {
    compileSdkVersion(Versions.android.compileSdk)
    buildToolsVersion(Versions.android.buildTools)

    defaultConfig {
        applicationId = "io.github.kdk96.tanto.sample"
        minSdkVersion(Versions.android.minSdk)
        targetSdkVersion(Versions.android.targetSdk)
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":library"))

    implementation(Deps.androidx.appCompat)
    implementation(Deps.androidx.coreKtx)
    implementation(Deps.androidx.fragmentKtx)
    implementation(Deps.androidx.material)
    implementation(Deps.androidx.preference)

    implementation(Deps.dagger)
    kapt(Deps.daggerCompiler)
}
