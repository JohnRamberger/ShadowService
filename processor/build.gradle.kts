plugins {
    kotlin("jvm")
    kotlin("kapt")
    `maven-publish`
    alias(libs.plugins.ktlint)
}

group = "com.jramberger.shadow"
version = "1.0-SNAPSHOT"

dependencies {
    kapt(libs.auto.service)
    implementation(libs.auto.service.annotations)
    implementation(libs.simple.yaml)
    implementation(project(":annotation"))
    implementation(project(":service"))
    implementation(libs.kaml)

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
