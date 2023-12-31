plugins {
  java
  idea
  kotlin("jvm")
  kotlin("plugin.jpa")
  kotlin("plugin.serialization")
  id("com.github.ben-manes.versions")
  id("org.springframework.boot") apply false
  id("com.github.johnrengelman.shadow") apply false
  id("org.jetbrains.kotlin.plugin.spring") apply true
}

fun isNonStable(version: String): Boolean {
  val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
  val regex = "^[0-9,\\.v\\-]+(-r)?$".toRegex()
  val isStable = stableKeyword || regex.matches(version)
  return isStable.not()
}

tasks.dependencyUpdates {
  rejectVersionIf {
    isNonStable(candidate.version)
  }
}

allprojects {
  apply(plugin = "idea")

  group = "io.github.joxit"
  version = "1.0-SNAPSHOT"

  repositories {
    mavenLocal()
    mavenCentral()
  }
}

subprojects {
  apply(plugin = "java")
  apply(plugin = "kotlin")
  apply(plugin = "org.jetbrains.kotlin.plugin.spring")
  apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
  apply(plugin = "kotlinx-serialization")

  tasks {
    compileKotlin {
      kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
      }
    }

  }

  java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:${property("version.spring.boot")}"))
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation(kotlin("serialization"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${property("version.kotlinx")}")
  }
}
