plugins {
    id("java")
    id("maven-publish")
    id("com.diffplug.spotless") version "8.0.0"
}

val libraryName = property("library_name").toString()
group = property("group")!!
version = property("version")!!

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
            artifactId = libraryName
            group = project.group
            version = project.version.toString()
            from(components["java"])
        }
    }

    repositories {
    }
}