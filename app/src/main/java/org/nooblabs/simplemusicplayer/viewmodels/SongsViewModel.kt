package org.nooblabs.simplemusicplayer.viewmodels

import android.content.ContentResolver
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.nooblabs.simplemusicplayer.loaders.SongLoader
import org.nooblabs.simplemusicplayer.models.PlayerStatus
import org.nooblabs.simplemusicplayer.models.Song

/**
 * [AndroidViewModel] for songs.
 */
class SongsViewModel(private val songLoader: SongLoader) : ViewModel() {

  private val songs = MutableLiveData<List<Song>>(emptyList())

  private val currentSongIndex = MutableLiveData<Int>(-1)

  private val playerStatus = MutableLiveData<PlayerStatus>(PlayerStatus.EMPTY)

  /**
   * Returns [LiveData] for [List] of [Song].
   */
  fun getSongs(): LiveData<List<Song>> = songs

  /**
   * Returns current [Song].
   */
  fun currentSong(): LiveData<Song> = Transformations.map(currentSongIndex) { index ->
    songs.value!![index]
  }

  fun currentPlayerStatus(): LiveData<PlayerStatus> = playerStatus

  /**
   * Select and play song at [index].
   */
  fun playSong(index: Int) {
    currentSongIndex.postValue(index)
    playerStatus.postValue(PlayerStatus.PLAYING)
  }

  /**
   * Toggle current playing song.
   */
  fun toggleSongStatus() {
    playerStatus.value?.let { status ->
      playerStatus.postValue(if (status == PlayerStatus.STOPPED) PlayerStatus.PLAYING else PlayerStatus.STOPPED)
    }
  }

  /**
   * Loads songs using [contentResolver].
   */
  fun loadSongs(contentResolver: ContentResolver) {
    songs.postValue(songLoader.getAllMusic(contentResolver))
  }

  /**
   * Plays next song in the list.
   */
  fun playNextSong() {
    val nextIndex = currentSongIndex.value!!.plus(1)
    if (nextIndex < songs.value!!.size) {
      playSong(nextIndex)
    } else {
      playerStatus.postValue(PlayerStatus.STOPPED)
    }
  }
}

/**
 * [ViewModelProvider.NewInstanceFactory] for [SongsViewModel].
 */
class SongsViewModelFactory(private val songLoader: SongLoader) :
  ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    SongsViewModel(songLoader) as T
}
