plugins {
  id("com.android.application")
  id("kotlin-android")
  id("kotlin-android-extensions")
  id("kotlin-kapt")
}

android {
  compileSdkVersion(Config.compileSdkVersion)

  defaultConfig {
    applicationId(Config.applicationId)
    minSdkVersion(Config.minSdkVersion)
    targetSdkVersion(Config.targetSdkVersion)
    versionCode(Config.versionCode)
    versionName(Config.versionName)

    testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
  }

  buildTypes {
    getByName("release") {
      minifyEnabled(false)
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )
    }
  }

  compileOptions {
    sourceCompatibility(JavaVersion.VERSION_1_8)
    targetCompatibility(JavaVersion.VERSION_1_8)
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }

  testOptions {
    unitTests.isIncludeAndroidResources = true
    unitTests.all(closureOf<Test> {
      useJUnitPlatform()
      testLogging.showStackTraces = true
    } as groovy.lang.Closure<Test>)
  }
}

dependencies {
  implementation(Deps.legacySupport)
  implementation(Deps.stdlib)
  implementation(Deps.corektx)
  implementation(Deps.appcompat)
  implementation(Deps.constraintLayout)
  implementation(Deps.lifecycleViewModel)
  implementation(Deps.fragmentKtx)
  implementation(Deps.logger)
  implementation(Deps.permission)
  implementation(Deps.recyclical)
  implementation(Deps.materialComponents)
  implementation(Deps.glide)
  implementation(Deps.glideCompiler)

  kapt(Deps.lifecycleCompiler)

  androidTestImplementation(Deps.kotest)
  androidTestImplementation(Deps.espresso)
}

