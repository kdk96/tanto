plugins {
    kotlin("jvm")
    id("ktlint-plugin")
}

dependencies {
    compileOnly(Deps.dagger)
}
