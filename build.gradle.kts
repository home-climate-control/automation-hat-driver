plugins {
    `version-catalog`
    `java-library`
    `maven-publish`
    jacoco
    id("net.ltgt.errorprone")
    id("org.sonarqube")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

group = "com.homeclimatecontrol"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {

    implementation(libs.log4j2.api)
    implementation(libs.log4j2.core)
    implementation(libs.pi4j)

    testImplementation(libs.mockito)
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testImplementation(libs.assertj)

    errorprone(libs.errorprone)
}

tasks.test {
    useJUnitPlatform()
}

sonarqube {
    properties {
        property("sonar.projectKey", "home-climate-control_automation-hat-driver")
        property("sonar.organization", "home-climate-control")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

jacoco {
    toolVersion = "0.8.7"
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}
