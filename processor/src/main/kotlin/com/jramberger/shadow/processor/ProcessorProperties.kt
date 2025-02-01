package com.jramberger.shadow.processor

object ProcessorProperties {
    val jarFile: String = System.getProperty("shadow.jarFile") ?: "./build/libs/shadow.jar"
    val outputDir: String = System.getProperty("shadow.outputDir") ?: "./"
    val templateFile: String = "template.yaml"
    val configFile = "samconfig.toml"
}
