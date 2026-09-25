import java.util.Properties

plugins {
    java
}
dependencies {
    testImplementation(platform("org.junit:junit-bom:6.1.3"))
    testImplementation(platform("io.cucumber:cucumber-bom:8.0.2"))
    testImplementation(platform("org.assertj:assertj-bom:3.27.7"))

    testImplementation("io.cucumber:cucumber-java")
    testImplementation("io.cucumber:cucumber-junit-platform-engine")
    testImplementation("org.assertj:assertj-core")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

repositories {
    mavenCentral()
}

tasks.named<Test>("test") {
    useJUnitPlatform {
        // When running an individual scenario, assume we only want to run 
        // Cucumber
        System.getProperty("cucumber.features")?.let { includeEngines("cucumber") }
    }

    // Tell Cucumber where to find the feature files.
    // See: https://docs.gradle.org/current/userguide/java_testing.html#sec:non-class-based-testing
    testDefinitionDirs.from("src/test/features")

    // Use properties from cucumber.properties for consistent behavior between
    // Gradle and the CLI (used by IDEA).
    systemProperties("src/test/resources/cucumber.properties")

    // Pass selected system properties to Cucumber
    System.getProperty("cucumber.features")?.let { systemProperty("cucumber.features", it) }
    System.getProperty("cucumber.filter.tags")?.let { systemProperty("cucumber.filter.tags", it) }
    System.getProperty("cucumber.filter.name")?.let { systemProperty("cucumber.filter.name", it) }
    System.getProperty("cucumber.plugin")?.let { systemProperty("cucumber.plugin", it) }
}

fun Test.systemProperties(path: String) {
    with(file(path).inputStream()) {
        val props = Properties();
        props.load(this)
        props.stringPropertyNames().forEach { systemProperty(it, props.getProperty(it)) }
    }
}
