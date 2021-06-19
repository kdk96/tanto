import java.io.File
import java.util.Properties

plugins {
    id("com.android.library")
    kotlin("android")
    id("ktlint-plugin")
    `maven-publish`
    signing
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

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(android.sourceSets.getByName("main").java.srcDirs)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = tantoGroup
                artifactId = findProperty("artifactId") as String
                version = tantoVersion

                from(components["debug"])

                artifact(sourcesJar)

                pom {
                    name.set("tanto")
                    description.set("tanto is lightweight component manager for Dagger 2")
                    url.set("https://github.com/kdk96/tanto")

                    licenses {
                        license {
                            name.set("MIT")
                            url.set("https://opensource.org/licenses/MIT")
                        }
                    }

                    developers {
                        developer {
                            id.set("kdk96")
                            name.set("Dias Kinayatov")
                            email.set("kdk96s@gmail.com")
                        }
                    }

                    scm {
                        connection.set("scm:git:git://github.com/kdk96/tanto.git")
                        developerConnection.set("scm:git:ssh://github.com/kdk96/tanto.git")
                        url.set("https://github.com/kdk96/tanto")
                    }
                }
            }
        }

        repositories {
            maven {
                name = "sonatype"
                url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                credentials {
                    val lp = Properties().apply {
                        project.rootProject.file("local.properties")
                            .takeIf(File::exists)
                            ?.inputStream()
                            ?.use(::load)
                    }
                    username = lp["OSSRH_USERNAME"].toString()
                    password = lp["OSSRH_PASSWORD"].toString()
                }
            }
        }
    }
    signing {
        sign(publishing.publications["release"])
    }
}
