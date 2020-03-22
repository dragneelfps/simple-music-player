package org.nooblabs.simplemusicplayer.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.nooblabs.simplemusicplayer.library.MusicLibrary
import org.nooblabs.simplemusicplayer.models.Song

/**
 * [AndroidViewModel] for songs.
 */
class SongsViewModel(application: Application, private val musicLibrary: MusicLibrary) :
  AndroidViewModel(application) {

  private val songs: MutableLiveData<List<Song>> by lazy {
    MutableLiveData<List<Song>>().also {
      loadSongs()
    }
  }

  /**
   * Returns [LiveData] for [List] of [Song].
   */
  fun getSongs(): LiveData<List<Song>> = songs

  private fun loadSongs(): List<Song> =
    musicLibrary.getAllMusic(this.getApplication<Application>().contentResolver)
}

/**
 * [ViewModelProvider.NewInstanceFactory] for [SongsViewModel].
 */
class SongsViewModelFactory(
  private val application: Application,
  private val musicLibrary: MusicLibrary
) :
  ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    SongsViewModel(application, musicLibrary) as T
}
