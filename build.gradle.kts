plugins {
    kotlin("js")
}

repositories {
    maven { url = uri("kotlin-build") }
    mavenLocal()
    mavenCentral()
}

val useWasm = true

dependencies {
    implementation(kotlin("stdlib-" + if (useWasm) "wasm" else "js"))
}

kotlin {
    js(IR) {
        binaries.executable()
        browser {
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>> {
    kotlinOptions {
        if (useWasm)
            freeCompilerArgs = freeCompilerArgs + listOf("-Xwasm")
    }
}