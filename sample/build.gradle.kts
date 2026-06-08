/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // Apply the shared build logic from a convention plugin.
    // The shared code is located in `buildSrc/src/main/kotlin/kotlin-jvm.gradle.kts`.
    id("buildsrc.convention.kotlin-jvm")

    // Apply the Application plugin to add support for building an executable JVM application.
    application
}

dependencies {
    implementation(project(":digest-util-ktx"))
    implementation(project(":crypto-util"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

sourceSets {
    // 获取并配置名为 "main" 的源集
    val main by getting {
        // 将 src/main/kotlin 同时注册为 Java 源码目录
        java {
            srcDir("src/main/kotlin")
        }
    }
}
