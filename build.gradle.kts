import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    kotlin("multiplatform")
}

repositories {
    mavenLocal()
    mavenCentral()
}

kotlin {
    wasm {
        moduleName = "game-of-life"
        binaries.executable()
        browser {
            commonWebpackConfig {
                devServer = KotlinWebpackConfig.DevServer(
                    open = mapOf(
                        "app" to mapOf(
                            "name" to "google chrome",
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
}