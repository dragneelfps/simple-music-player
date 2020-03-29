package org.nooblabs.simplemusicplayer

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.song_mini
import org.nooblabs.simplemusicplayer.callbacks.SelectedSongListener
import org.nooblabs.simplemusicplayer.loaders.SongLoader
import org.nooblabs.simplemusicplayer.models.PlayerStatus
import org.nooblabs.simplemusicplayer.models.Song
import org.nooblabs.simplemusicplayer.ui.SongListFragment
import org.nooblabs.simplemusicplayer.viewmodels.SongsViewModel
import org.nooblabs.simplemusicplayer.viewmodels.SongsViewModelFactory

/**
 * Main Activity.
 */
class MainActivity : AppCompatActivity(), SelectedSongListener, MediaPlayer.OnCompletionListener {

  private lateinit var mediaPlayer: MediaPlayer
  private val songsViewModel: SongsViewModel by viewModels { SongsViewModelFactory(SongLoader()) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(findViewById(R.id.main_action_bar))

    supportFragmentManager.beginTransaction().apply {
      add(
        R.id.fragment_song_list,
        SongListFragment(songsViewModel, this@MainActivity as SelectedSongListener)
      )
      commit()
    }

    song_mini.setOnClickListener {
      songsViewModel.toggleSongStatus()
    }

    mediaPlayer = MediaPlayer().apply {
      setAudioAttributes(
        AudioAttributes.Builder()
          .setUsage(AudioAttributes.USAGE_MEDIA)
          .build()
      )
    }

    songsViewModel.currentPlayerStatus().observe(this, Observer { status ->
      when (status) {
        PlayerStatus.PLAYING -> {
          song_mini.show()
          song_mini.icon = ContextCompat.getDrawable(this, R.drawable.ic_pause)
          mediaPlayer.start()
        }
        PlayerStatus.STOPPED -> {
          song_mini.icon = ContextCompat.getDrawable(this, R.drawable.ic_play)
          mediaPlayer.pause()
        }
        PlayerStatus.EMPTY -> {
          song_mini.hide()
        }
      }
    })
  }

  override fun onCurrentSongChanged(song: Song) {
    song_mini.icon = ContextCompat.getDrawable(this, R.drawable.ic_pause)
    song_mini.text = song.title
    mediaPlayer.apply {
      reset()
      setDataSource(this@MainActivity, song.uri)
      setOnCompletionListener(this@MainActivity)
      prepare()
      start()
    }
  }

  companion object {
    private val TAG = MainActivity::class.simpleName
  }

  override fun onCompletion(mp: MediaPlayer) {
    songsViewModel.playNextSong()
  }
}
