import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.61"
    application
}

version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

application {
    mainClassName = "MainKt"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(group = "io.ktor", name = "ktor-server-core", version = "1.2.6")
    implementation(group = "io.ktor", name = "ktor-server-netty", version = "1.2.6")
    implementation(group = "io.ktor", name = "ktor-gson", version = "1.2.6")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}