plugins {
    kotlin("jvm") version "2.1.0"
    id("java-gradle-plugin")
//    kotlin("kapt") version "2.1.0"
    alias(libs.plugins.ktlint) apply false
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
