import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig
import org.jetbrains.kotlin.gradle.targets.js.dsl.KotlinJsTargetDsl

plugins {
    kotlin("multiplatform") version "1.7.255-SNAPSHOT"
}

repositories {
    mavenLocal()
    mavenCentral()
}

kotlin {
    wasm {
        setupTarget()
    }
    js(IR) {
        setupTarget()
    }
}

fun KotlinJsTargetDsl.setupTarget() {
    moduleName = "game-of-life"
    binaries.executable()
    browser {
        commonWebpackConfig {
            devServer = KotlinWebpackConfig.DevServer(
                open = mapOf(
                    "app" to mapOf(
                        "name" to "google chrome canary",
                        "arguments" to listOf(
                            "--js-flags=" +
                                    "--experimental-wasm-typed-funcref " +
                                    "--experimental-wasm-gc " +
                                    "--experimental-wasm-eh"
                        )
                    )
                ),
                static = devServer?.static
            )
        }
    }
}
