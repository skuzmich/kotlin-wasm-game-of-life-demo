plugins {
    kotlin("js")
}

group = "me.user"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-wasm"))
}

kotlin {
    js(IR) {
        binaries.executable()
        browser {

        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>() {
    kotlinOptions {
        freeCompilerArgs += listOf("-Xwasm")
    }
}