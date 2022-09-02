import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
}

group = "de.dinomarlir"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.github.ajalt.mordant:mordant:2.0.0-beta7")
    implementation("com.github.ajalt.clikt:clikt:3.5.0")

    implementation("io.ktor:ktor-client-core:2.1.0")
    implementation("io.ktor:ktor-client-cio:2.1.0")
    implementation("io.ktor:ktor-client-content-negotiation:2.1.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.1.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
}

application {
    mainClass.set("de.dinomarlir.dinocloud.ApplicationKt")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}