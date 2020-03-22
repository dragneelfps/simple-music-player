package org.nooblabs.simplemusicplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Main Activity.
 */
class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  companion object {
    private val TAG = MainActivity::class.simpleName
  }
}
