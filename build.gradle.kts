plugins {
    java
}
dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.1"))
    testImplementation(platform("io.cucumber:cucumber-bom:7.32.0"))
    testImplementation(platform("org.assertj:assertj-bom:3.27.6"))

    testImplementation("io.cucumber:cucumber-java")
    testImplementation("io.cucumber:cucumber-junit-platform-engine")
    testImplementation("org.junit.platform:junit-platform-suite")
    testImplementation("org.assertj:assertj-core")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

repositories {
    mavenCentral()
}

tasks.withType<Test> {
    useJUnitPlatform {
        // When running an individual scenario, assume we only want to run 
        // Cucumber
        System.getProperty("cucumber.features")?.let { includeEngines("cucumber") }
    }

    // Pass selected system properties to Cucumber
    System.getProperty("cucumber.features")?.let { systemProperty("cucumber.features", it) }
    System.getProperty("cucumber.filter.tags")?.let { systemProperty("cucumber.filter.tags", it) }
    System.getProperty("cucumber.filter.name")?.let { systemProperty("cucumber.filter.name", it) }
    System.getProperty("cucumber.plugin")?.let { systemProperty("cucumber.plugin", it) }

    // Work around. Gradle does not include enough information to disambiguate
    // between different examples and scenarios.
    systemProperty("cucumber.junit-platform.naming-strategy", "long")
}
