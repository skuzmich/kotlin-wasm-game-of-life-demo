pluginManagement {
    plugins {
        id("org.jetbrains.kotlin.multiplatform") version "1.6.255-SNAPSHOT"
    }
    resolutionStrategy {
    }
    repositories {
        maven { url = uri("kotlin-build") }
        mavenLocal()
        mavenCentral()
    }
}

rootProject.name = "game_of_life"