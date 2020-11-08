plugins {
    id("com.android.library")
    kotlin("android")
    id("ktlint-plugin")
    id("publish-plugin")
}

val tantoGroup = findProperty("group") as String
val tantoVersion = findProperty("version") as String

version = tantoGroup
group = tantoVersion

android {
    compileSdkVersion(Versions.android.compileSdk)
    buildToolsVersion(Versions.android.buildTools)

    defaultConfig {
        minSdkVersion(Versions.android.minSdk)
        targetSdkVersion(Versions.android.targetSdk)
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
    api(project(":tanto"))

    compileOnly(Deps.dagger)

    compileOnly(Deps.androidx.fragment)
}

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(android.sourceSets.getByName("main").java.srcDirs)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>(Publications.tantoAndroid) {
                groupId = tantoGroup
                artifactId = Publications.tantoAndroid
                version = tantoVersion

                from(components["release"])

                artifact(sourcesJar)
            }
        }
    }
}
