plugins {
    kotlin("jvm") version "1.9.22"
    id("java-gradle-plugin")
//    kotlin("kapt") version "2.1.0"
}

group = "com.jramberger"
version = "1.0-SNAPSHOT"

subprojects {
    repositories {
        mavenCentral()
    }
}

repositories {
    mavenCentral()
}

tasks.register<Jar>("jar") {
    archiveBaseName.set("ShadowService")
    archiveVersion.set(version.toString())
    from(sourceSets.main.get().output)
}
