plugins {
    java
    `java-library`
    jacoco
    alias(libs.plugins.errorprone)
    alias(libs.plugins.sonarqube)

    // If this project is included as a submodule or is in a Git worktree, this plugin chokes on non-existing ./.git
    // and produces very long and annoying unsuppressable output.

    // See https://github.com/n0mer/gradle-git-properties/issues/175 - will re-enable when fixed. Not worth it now.
    // alias(libs.plugins.git.properties)

    alias(libs.plugins.gradle.versions)
    alias(libs.plugins.gradle.dependency.analysis)
    alias(libs.plugins.gradle.doctor)
}

apply(plugin = "java-library")
apply(plugin = "maven-publish")
apply(plugin = "jacoco")
apply(plugin = libs.plugins.errorprone.get().pluginId)

group = "com.homeclimatecontrol"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

jacoco {
    toolVersion = libs.versions.jacoco.get()
}

tasks.compileJava {
    options.release = 11
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

dependencies {

    api(libs.log4j.api)
    implementation(libs.log4j.core)

    implementation(libs.pi4j)

    testImplementation(libs.mockito)
    testImplementation(libs.assertj.core)
    testImplementation(libs.junit5.api)

    testRuntimeOnly(libs.junit5.engine)

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
