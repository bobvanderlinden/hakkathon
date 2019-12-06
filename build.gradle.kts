import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.41"
}

group = "com.softwarebakery"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    compile(group = "io.ktor", name = "ktor-server-core", version = "1.2.6")
    compile(group = "io.ktor", name = "ktor-server-netty", version = "1.2.6")
//    compile(group = "com.graphql-java", name = "graphql-java", version = "11.0")
    implementation(group = "com.github.kittinunf.fuel", name = "fuel", version = "2.2.1")
    implementation(group = "com.github.kittinunf.fuel", name = "fuel-json", version = "2.2.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}