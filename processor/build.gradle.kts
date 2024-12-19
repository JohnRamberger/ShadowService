plugins {
    kotlin("jvm")
    kotlin("kapt")
    `maven-publish`
}

group = "com.jramberger.shadow"
version = "1.0-SNAPSHOT"

dependencies {
    kapt("com.google.auto.service:auto-service:1.1.1")
    implementation("com.google.auto.service:auto-service-annotations:1.1.1")
    implementation(project(":annotation"))

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
