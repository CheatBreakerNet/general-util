plugins {
    id("java")
    id("maven-publish")
    id("com.diffplug.spotless") version "8.0.0"
}

group = property("group")!!
version = property("version")!!

base {
    archivesName.set(property("name").toString())
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("commons-io:commons-io:${property("commons_io_version")}")
    implementation("com.google.guava:guava:${property("guava_version")}")
    implementation("com.google.guava:failureaccess:${property("guava_failureaccess_version")}")
}

// Header
spotless {
    val licenseHeader = rootProject.file("HEADER")
    lineEndings = com.diffplug.spotless.LineEnding.UNIX

    java {
        licenseHeaderFile(licenseHeader)
    }

    kotlin {
        licenseHeaderFile(licenseHeader)
    }
}

// Maven Publishing
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "${project.group}:${base.archivesName}:${project.version}"
            from(components["java"])
        }
    }

    repositories {
    }
}