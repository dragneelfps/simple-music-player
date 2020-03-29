package org.nooblabs.simplemusicplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.player
import org.nooblabs.simplemusicplayer.ui.adaptors.PlayerViewPagerAdaptor

/**
 * Main Activity.
 */
class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    player.adapter = PlayerViewPagerAdaptor(supportFragmentManager)
  }

  companion object {
    private val TAG = MainActivity::class.simpleName
  }
}
