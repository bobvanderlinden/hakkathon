import com.apollographql.apollo.gradle.ApolloExtension
import io.kotless.DSLType
import io.kotless.plugin.gradle.dsl.Webapp.Route53
import io.kotless.plugin.gradle.dsl.kotless

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val kotless_version: String by project

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath("com.apollographql.apollo:apollo-gradle-plugin:1.2.2")
    }
}

plugins {
    kotlin("jvm") version "1.3.61"
    application
    id("io.kotless") version "0.1.2"
}

group = "io.github.bobvanderlinden"
version = "0.0.1"

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenLocal()
    jcenter()
    maven { url = uri("https://kotlin.bintray.com/ktor") }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-gson:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-core-jvm:$ktor_version")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")

    implementation("com.apollographql.apollo:apollo-runtime:1.2.2")

    compileOnly("org.jetbrains:annotations:13.0")
    testCompileOnly("org.jetbrains:annotations:13.0")

    implementation("io.kotless", "lang", kotless_version)
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")

apply(plugin = "com.apollographql.android")

configure<ApolloExtension> { setGenerateKotlinModels(true) }

kotless {
    config {
        bucket = "such.spacious.very.storage.many.bucket"
        prefix = "short"

        dsl {
            type = DSLType.Kotless

            workDirectory = file("src/main/static")
        }

        terraform {
            profile = "bleep-bloop-profile"
            region = "eu-central-1"
        }
    }

    webapp {
        lambda {
            kotless {
                packages = setOf("io.github.bobvanderlinden")
            }
        }

        route53 = Route53("short", "meuk.me.uk")
    }

    extensions {
        terraform {
            files {
                add(file("src/main/tf/extensions.tf"))
            }
        }
    }
}
