/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

plugins {
    id("java")
    alias(libs.plugins.publisher)
}

dependencies {
    implementation(project(":digest-util"))
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.test {
    useJUnitPlatform()
}
