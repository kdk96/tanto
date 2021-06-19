buildscript {

    repositories {
        mavenCentral()
        google()
    }

    dependencies {
        classpath(Deps.kotlinGradlePlugin)
        classpath(Deps.androidGradlePlugin)
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
    }
}
