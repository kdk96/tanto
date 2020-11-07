plugins {
    kotlin("jvm")
    id("ktlint-plugin")
    id("publish-plugin")
}

val tantoGroup = findProperty("group") as String
val tantoVersion = findProperty("version") as String

version = tantoGroup
group = tantoVersion

dependencies {
    compileOnly(Deps.dagger)
}

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.getByName("main").allSource)
}

publishing {
    publications {
        create<MavenPublication>(Publications.tanto) {
            groupId = tantoGroup
            artifactId = Publications.tanto
            version = tantoVersion

            from(components["kotlin"])

            artifact(sourcesJar)
        }
    }
}
