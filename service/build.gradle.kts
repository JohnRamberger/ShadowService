plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "2.1.0"
    `maven-publish`
    alias(libs.plugins.ktlint)
}

group = "com.jramberger.shadow"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(20)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
