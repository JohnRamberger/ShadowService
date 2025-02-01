plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "Shadow"
include("processor")
include("plugin")
include("annotation")
include("service")