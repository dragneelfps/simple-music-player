import io.gitlab.arturbosch.detekt.Detekt

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
  repositories {
    google()
    jcenter()
  }
  dependencies {
    classpath("com.android.tools.build:gradle:4.1.0-alpha03")
    classpath(kotlin("gradle-plugin", version = "1.3.70"))
    classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Deps.Version.detektVersion}")

    // NOTE: Do not place your application dependencies here; they belong
    // in the individual module build.gradle files
  }
}

plugins {
  id("io.gitlab.arturbosch.detekt") version Deps.Version.detektVersion
}

allprojects {
  repositories {
    google()
    jcenter()
  }

  apply(plugin = "io.gitlab.arturbosch.detekt")

  dependencies {
    detektPlugins(Deps.formatting)
  }
}

tasks.register("clean", Delete::class) {
  delete(rootProject.buildDir)
}

tasks {
  withType<Detekt> {
    jvmTarget = "1.8"
  }
}

detekt {
  config = files("config/detekt/detekt.yml")
}
