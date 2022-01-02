enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    plugins {
        id("com.github.spotbugs") version "4.0.5"
        id("net.ltgt.errorprone") version "2.0.2"
        id("org.sonarqube") version "3.3"
        id("com.autonomousapps.dependency-analysis") version "0.79.0"
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("log4jVersion", "2.17.0")
            version("junitVersion", "5.8.2")
            alias("log4j2-api").to("org.apache.logging.log4j","log4j-api").versionRef("log4jVersion")
            alias("log4j2-core").to("org.apache.logging.log4j", "log4j-core").versionRef("log4jVersion")
            alias("pi4j").to("com.pi4j:pi4j-core:1.4")
            alias("mockito").to("org.mockito:mockito-core:4.2.0")
            alias("junit-jupiter-api").to("org.junit.jupiter","junit-jupiter-api").versionRef("junitVersion")
            alias("junit-jupiter-engine").to("org.junit.jupiter", "junit-jupiter-engine").versionRef("junitVersion")
            alias("assertj").to("org.assertj:assertj-core:3.21.0")
            alias("errorprone").to("com.google.errorprone:error_prone_core:2.10.0")
        }
    }
}

rootProject.name = "automation-hat-driver"
