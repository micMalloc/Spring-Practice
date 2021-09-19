plugins {
    kotlin("jvm") version "1.5.10"
}

group = "kr.heesu.practice"
version = "0.0.1-RELEASE"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
}