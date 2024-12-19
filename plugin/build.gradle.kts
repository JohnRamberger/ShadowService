plugins {
    kotlin("jvm")
    id("java-gradle-plugin")
    id("com.gradle.plugin-publish") version "1.3.0" // For publishing the plugin
}

version = "1.0.0"
group = "com.jramberger"

dependencies {
    implementation(project(":processor"))
    implementation(gradleApi())

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}

gradlePlugin {
    website = "https://github.com/JohnRamberger/ShadowService"
    vcsUrl = "https://github.com/JohnRamberger/ShadowService.git"

    plugins {
        create("shadow") {
            id = "com.jramberger.shadow"
            displayName = "ShadowService"
            description = "Generate AWS SAM configuration files directly from your code."
            tags = listOf("aws", "sam", "shadow")
            implementationClass = "com.jramberger.shadow.plugin.ShadowPlugin"
        }
    }
}
