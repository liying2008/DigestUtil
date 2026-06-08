/*
 * Copyright 2017-present Li Ying.
 * Licensed under the MIT License.
 */

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.publisher) apply false
}

allprojects {
    tasks.withType<Javadoc> {
        // set Javadoc encoding to UTF-8
        options.encoding = "UTF-8"
        // 关闭 DocLint 检查
        (options as StandardJavadocDocletOptions).addStringOption("Xdoclint:none", "-quiet")
    }
}
