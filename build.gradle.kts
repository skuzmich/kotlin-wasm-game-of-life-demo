plugins {
    kotlin("multiplatform") version "1.8.20-Beta"
}

repositories {
    mavenCentral()
}

kotlin {
    wasm {
        moduleName = "game-of-life"
        binaries.executable()
        browser {
        }
    }
    js(IR) {
        moduleName = "game-of-life"
        binaries.executable()
        browser {
        }
    }
}