@file:Suppress("UndocumentedPublicClass")

object Deps {

  object Version {

    const val kotlinVersion = "1.3.70"
    const val lifecycleVersion = "2.2.0"
    const val detektVersion = "1.7.0-beta2"
  }

  const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlinVersion}"
  const val lifecycleViewModel =
    "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycleVersion}"
  const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Version.lifecycleVersion}"

  const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
  const val appcompat = "androidx.appcompat:appcompat:1.1.0"
  const val corektx = "androidx.core:core-ktx:1.2.0"
  const val legacySupport = "androidx.legacy:legacy-support-v4:1.0.0"
  const val fragmentKtx = "androidx.fragment:fragment-ktx:1.2.3"
  const val materialComponents = "com.google.android.material:material:1.1.0"
  const val viewpager2 = "androidx.viewpager2:viewpager2:1.0.0"

  const val recyclical = "com.afollestad:recyclical:1.1.1"
  const val permission = "com.github.florent37:runtime-permission-kotlin:1.1.2"
  const val glide = "com.github.bumptech.glide:glide:4.11.0"
  const val glideCompiler = "com.github.bumptech.glide:compiler:4.11.0"
  const val logger = "com.orhanobut:logger:2.2.0"
  const val formatting = "io.gitlab.arturbosch.detekt:detekt-formatting:${Version.detektVersion}"

  const val kotest = "io.kotlintest:kotlintest-runner-junit4:4.0.0"
  const val espresso = "androidx.test.espresso:espresso-core:3.2.0"
}
