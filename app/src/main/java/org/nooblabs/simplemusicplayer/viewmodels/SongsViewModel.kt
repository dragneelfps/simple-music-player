package org.nooblabs.simplemusicplayer.viewmodels

import android.content.ContentResolver
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.nooblabs.simplemusicplayer.loaders.SongLoader
import org.nooblabs.simplemusicplayer.models.Song

/**
 * [AndroidViewModel] for songs.
 */
class SongsViewModel(private val songLoader: SongLoader) : ViewModel() {

  private val songs: MutableLiveData<List<Song>> by lazy {
    MutableLiveData<List<Song>>()
  }

  private val currentSongIndex: MutableLiveData<Int> by lazy {
    MutableLiveData<Int>()
  }

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

  /**
   * Select and play song at [index].
   */
  fun playSong(index: Int) {
    currentSongIndex.postValue(index)
  }

  /**
   * Loads songs using [contentResolver].
   */
  fun loadSongs(contentResolver: ContentResolver) {
    songs.postValue(songLoader.getAllMusic(contentResolver))
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
