import com.jfrog.bintray.gradle.BintrayExtension
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.*

plugins {
    kotlin("jvm") version "1.3.31"
    maven
    id("org.jetbrains.dokka") version "0.9.18"
    id("com.jfrog.bintray") version "1.8.4"
    `maven-publish`
}

fun readProperties(propertiesFile: File) = Properties().apply {
    propertiesFile.inputStream().use { fis ->
        load(fis)
    }
}

val gradleProperties = readProperties(File(project.rootDir, "gradle.properties"))
val localProperties = readProperties(File(project.rootDir, "local.properties"))

group = gradleProperties.getProperty("LIB_GROUP")
version = gradleProperties.getProperty("LIB_VERSION")

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("junit:junit:4.12")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

//==========================

val dokka by tasks.getting(DokkaTask::class) {
    outputFormat = "javadoc"
    outputDirectory = "$buildDir/javadoc"
    jdkVersion = 8
}

val dokkaJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles Kotlin docs with Dokka"
    archiveClassifier.set("javadoc")
    from(dokka)
}
artifacts.add("archives", dokkaJar)

val sourcesJar by tasks.creating(Jar::class) {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "Assembles sources JAR"
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}
artifacts.add("archives", sourcesJar)

val jar by tasks.getting(Jar::class) {
    manifest.attributes.apply {
        put("Implementation-Title", "Digest Utils Lib")
        put("Implementation-Version", project.version)
        put("Built-By", System.getProperty("user.name"))
        put("Built-JDK", System.getProperty("java.version"))
        put("Built-Gradle", project.gradle.gradleVersion)
    }
}

//=====================

val siteUrl: String = gradleProperties.getProperty("LIB_SITE_URL")
val gitUrl: String = gradleProperties.getProperty("LIB_GIT_URL")
val libRepo: String = gradleProperties.getProperty("LIB_REPO")
val libName: String = gradleProperties.getProperty("LIB_NAME")
val libArtifactId: String = gradleProperties.getProperty("LIB_ARTIFACT_ID")
val libDesc: String = gradleProperties.getProperty("LIB_DESC")
val libIssueTrackerUrl: String = gradleProperties.getProperty("LIB_ISSUE_TRACKER_URL")
val libLicense: String = gradleProperties.getProperty("LIB_LICENSE")
val libLicenseUrl: String = gradleProperties.getProperty("LIB_LICENSE_URL")
val libGithubRepo: String = gradleProperties.getProperty("LIB_GITHUB_REPO")

publishing {
    publications.create<MavenPublication>("CentralPublication") {
        groupId = project.group.toString()
        artifactId = libArtifactId
        version = project.version.toString()
        pom {
            packaging = gradleProperties.getProperty("LIB_PACKAGING")
            name.set(libName)
            url.set(siteUrl)
            description.set(libDesc)
            artifactId = libArtifactId
            licenses {
                license {
                    name.set(libLicense)
                    url.set(libLicenseUrl)
                    distribution.set("repo")
                }
            }
            developers {
                developer {
                    id.set(gradleProperties.getProperty("LIB_DEVELOPER_ID"))
                    name.set(gradleProperties.getProperty("LIB_DEVELOPER_NAME"))
                    email.set(gradleProperties.getProperty("LIB_DEVELOPER_EMAIL"))
                }
            }
            scm {
                connection.set(siteUrl)
                developerConnection.set(gitUrl)
                url.set(siteUrl)
            }
        }
        from(components["java"])
        artifact(sourcesJar)
        artifact(dokkaJar)
    }
}

bintray {
    user = localProperties.getProperty("BINTRAY_USER")
    key = localProperties.getProperty("BINTRAY_KEY")
    dryRun = false //[Default: false] Whether to run this as dry-run, without deploying
    publish = true //[Default: false] Whether version should be auto published after an upload
    override = false //[Default: false] Whether to override version artifacts already published

    setPublications("CentralPublication")
    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = libRepo
        name = libName
        desc = libDesc
        websiteUrl = siteUrl
        issueTrackerUrl = libIssueTrackerUrl
        vcsUrl = gitUrl
        setLicenses(libLicense)
        setLabels(gradleProperties.getProperty("LIB_PACKAGING"), gradleProperties.getProperty("LIB_LABEL"))
        publicDownloadNumbers = true

        githubRepo = libGithubRepo //Optional Github repository
        githubReleaseNotesFile = gradleProperties.getProperty("LIB_GITHUB_RELEASE_NOTES_FILE") //Optional Github readme file

        //Optional version descriptor
        version(delegateClosureOf<BintrayExtension.VersionConfig> {
            //Optional configuration for GPG signing
            gpg(delegateClosureOf<BintrayExtension.GpgConfig> {
                sign = true //Determines whether to GPG sign the files. The default is false
            })
            //Optional configuration for Maven Central sync of the version
            mavenCentralSync(delegateClosureOf<BintrayExtension.MavenCentralSyncConfig> {
                sync = true
                //[Default: true] Determines whether to sync the version to Maven Central.
                user = localProperties.getProperty("OSS_USER_TOKEN_KEY") //OSS user token: mandatory
                password = localProperties.getProperty("OSS_USER_TOKEN_PASSWORD")
//OSS user password: mandatory
                close = "1"
                //Optional property. By default the staging repository is closed and artifacts are released to Maven Central. You can optionally turn this behaviour off (by puting 0 as value) and release the version manually.
            })
        })
    })
}
