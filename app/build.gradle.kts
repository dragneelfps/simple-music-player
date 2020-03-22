import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
  id("com.android.application")
  id("kotlin-android")
  id("kotlin-android-extensions")
  id("kotlin-kapt")
}

android {
  compileSdkVersion(29)

  defaultConfig {
    applicationId("org.nooblabs.simplemusicplayer")
    minSdkVersion(23)
    targetSdkVersion(29)
    versionCode(1)
    versionName("1.0")

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
}

dependencies {
  implementation("androidx.legacy:legacy-support-v4:1.0.0")
  implementation("org.jetbrains.kotlin:kotlin-stdlib:${KotlinCompilerVersion.VERSION}")
  implementation("androidx.core:core-ktx:1.2.0")
  implementation("androidx.appcompat:appcompat:1.1.0")
  implementation("androidx.constraintlayout:constraintlayout:1.1.3")
  implementation(Deps.Lifecycle.viewModel)
  implementation("androidx.fragment:fragment-ktx:1.2.3")
  implementation(Deps.Logging.logger)
  implementation(Deps.permission)

  kapt(Deps.Lifecycle.compiler)

  testImplementation("junit:junit:4.12")
  androidTestImplementation("androidx.test.ext:junit:1.1.1")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}

