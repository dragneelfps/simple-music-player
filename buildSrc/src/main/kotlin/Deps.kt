@file:Suppress("UndocumentedPublicClass")

object Deps {

  object Plugins {

    object Detekt {
      const val detektVersion = "1.7.0-beta2"
      const val formatting = "io.gitlab.arturbosch.detekt:detekt-formatting:$detektVersion"
    }
  }
  object Lifecycle {

    private const val lifecycleVersion = "2.2.0"

    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"

    const val compiler = "androidx.lifecycle:lifecycle-compiler:$lifecycleVersion"
  }
  object Logging {

    const val logger = "com.orhanobut:logger:2.2.0"
  }

  const val permission = "com.github.florent37:runtime-permission-kotlin:1.1.2"
}
