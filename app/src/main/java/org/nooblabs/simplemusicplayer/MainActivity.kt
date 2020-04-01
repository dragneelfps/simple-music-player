package org.nooblabs.simplemusicplayer

import android.Manifest
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.florent37.runtimepermission.kotlin.askPermission
import kotlinx.android.synthetic.main.activity_main.song_mini
import org.nooblabs.simplemusicplayer.callbacks.PlayerStatusListener
import org.nooblabs.simplemusicplayer.callbacks.SongListItemListener
import org.nooblabs.simplemusicplayer.models.PlayerStatus
import org.nooblabs.simplemusicplayer.models.Song
import org.nooblabs.simplemusicplayer.service.MusicRemote
import org.nooblabs.simplemusicplayer.ui.SongListFragment
import org.nooblabs.simplemusicplayer.viewmodels.SongsViewModel

/**
 * Main Activity.
 */
class MainActivity : AppCompatActivity(), PlayerStatusListener {

  private val songsViewModel: SongsViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setupUi()
  }

  override fun onAttachFragment(fragment: Fragment) {
    if (fragment is SongListFragment) {
      fragment.songListItemListener = object : SongListItemListener {
        override fun onSongClick(song: Song) {
          playSong(song)
        }
      }
    }
  }

  override fun onStart() {
    super.onStart()
    MusicRemote.startAndBindToService(this)
    MusicRemote.playerStatusListener = this
  }

  override fun onStop() {
    super.onStop()
    MusicRemote.stopAndUnbindService(this)
  }

  override fun onCurrentSongChanged(song: Song) {
    song_mini.icon = ContextCompat.getDrawable(this, R.drawable.ic_pause)
    song_mini.text = song.title
  }

  private fun setupUi() {
    setContentView(R.layout.activity_main)
    setSupportActionBar(findViewById(R.id.main_action_bar))

    supportFragmentManager.beginTransaction().apply {
      add(
        R.id.fragment_song_list,
        SongListFragment()
      )
      commit()
    }

    setupObservers()
  }

  private fun setupObservers() {
    askPermission(Manifest.permission.READ_EXTERNAL_STORAGE) {
      songsViewModel.loadSongs(contentResolver)
      songsViewModel.songs().observe(this, Observer { songs ->
        loadSongs(songs)
      })
    }

    song_mini.setOnClickListener {
      toggle()
    }

    songsViewModel.currentPlayerStatus().observe(this, Observer { status ->
      when (status) {
        PlayerStatus.PLAYING -> {
          song_mini.show()
          song_mini.icon = ContextCompat.getDrawable(this, R.drawable.ic_pause)
        }
        PlayerStatus.STOPPED -> {
          song_mini.icon = ContextCompat.getDrawable(this, R.drawable.ic_play)
        }
        else -> {
          song_mini.hide()
        }
      }
    })
  }

  private fun loadSongs(songs: List<Song>) {
    val songListFragment =
      supportFragmentManager.findFragmentById(R.id.fragment_song_list) as SongListFragment
    songListFragment.loadSongs(songs)
  }

  private fun playSong(song: Song) {
    MusicRemote.play(song)
    songsViewModel.setCurrentStatus(PlayerStatus.PLAYING)
  }

  private fun toggle() {
    MusicRemote.toggle()
    songsViewModel.toggleCurrentStatus()
  }
}
