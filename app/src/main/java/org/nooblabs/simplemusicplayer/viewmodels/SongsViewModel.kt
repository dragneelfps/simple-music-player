package org.nooblabs.simplemusicplayer.viewmodels

import android.content.ContentResolver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.nooblabs.simplemusicplayer.loaders.SongLoader
import org.nooblabs.simplemusicplayer.models.PlayerStatus
import org.nooblabs.simplemusicplayer.models.Song

/**
 * asd.
 */
class SongsViewModel : ViewModel() {

  private val songLoader = SongLoader()
  private val songs = MutableLiveData<List<Song>>()
  private val currentPlayerStatus = MutableLiveData(PlayerStatus.EMPTY)

  fun songs(): LiveData<List<Song>> = songs

  fun currentPlayerStatus(): LiveData<PlayerStatus> = currentPlayerStatus

  fun toggleCurrentStatus() {
    when (currentPlayerStatus.value) {
      PlayerStatus.PLAYING -> currentPlayerStatus.postValue(PlayerStatus.STOPPED)
      PlayerStatus.STOPPED -> currentPlayerStatus.postValue(PlayerStatus.PLAYING)
      else -> Unit
    }
  }

  fun setCurrentStatus(playerStatus: PlayerStatus) {
    currentPlayerStatus.postValue(playerStatus)
  }

  fun loadSongs(contentResolver: ContentResolver) {
    songs.postValue(songLoader.getAllMusic(contentResolver))
  }
}
