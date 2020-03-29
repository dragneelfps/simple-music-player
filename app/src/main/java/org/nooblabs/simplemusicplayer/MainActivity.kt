package org.nooblabs.simplemusicplayer

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.nooblabs.simplemusicplayer.loaders.SongLoader
import org.nooblabs.simplemusicplayer.ui.SongListFragment
import org.nooblabs.simplemusicplayer.viewmodels.SongsViewModel
import org.nooblabs.simplemusicplayer.viewmodels.SongsViewModelFactory

/**
 * Main Activity.
 */
class MainActivity : AppCompatActivity() {

  private val songsViewModel: SongsViewModel by viewModels { SongsViewModelFactory(SongLoader()) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(findViewById(R.id.main_action_bar))

    supportFragmentManager.beginTransaction().apply {
      add(R.id.fragment_song_list, SongListFragment(songsViewModel))
      commit()
    }
  }

  companion object {
    private val TAG = MainActivity::class.simpleName
  }
}
