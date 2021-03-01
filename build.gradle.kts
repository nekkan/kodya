import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
    kotlin("multiplatform") version "1.4.30"
    kotlin("plugin.serialization") version "1.4.30"
}

buildscript {
    dependencies {
        classpath("org.jetbrains.kotlinx:atomicfu-gradle-plugin:0.15.1")
    }
}


repositories {
    mavenCentral()
}

enum class NativePlatform(val callback: KotlinMultiplatformExtension.() -> Unit) {
    Windows({ mingwX64("native") }),
    Linux({ linuxX64("native") }),
    MacOS({ macosX64("native") }),
    Other({ throw GradleException("Host OS is not supported in Kotlin/Native.") });
}

fun Project.setupPlatforms() = kotlin {
    /* Targets configuration omitted.
    *  To find out how to configure the targets, please follow the link:
    *  https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets */
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }
    js(LEGACY) {
        binaries.executable()
        nodejs()
    }

    val hostOs = System.getProperty("os.name")
    val nativePlatform = when {
        hostOs.startsWith("Windows") -> NativePlatform.Windows
        hostOs == "Mac OS X" -> NativePlatform.MacOS
        hostOs == "Linux" -> NativePlatform.Linux
        else -> NativePlatform.Other
    }
    nativePlatform.callback(this)

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
    }
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.plugin.serialization")
    apply(plugin = "kotlinx-atomicfu")

    setupPlatforms()
}