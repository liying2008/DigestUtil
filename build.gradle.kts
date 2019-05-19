subprojects {
    project.buildDir = file(rootProject.buildDir.absolutePath + "/" + project.name)
}

tasks.register<Delete>("cleanAll") {
    delete(rootProject.buildDir)
    subprojects {
        delete("out")
    }
}

allprojects {
    repositories {
        maven("https://maven.aliyun.com/repository/public")
        mavenCentral()
    }
}
