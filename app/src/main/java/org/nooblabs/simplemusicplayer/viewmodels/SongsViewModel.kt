package org.nooblabs.simplemusicplayer.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.nooblabs.simplemusicplayer.loaders.SongLoader
import org.nooblabs.simplemusicplayer.models.Song

/**
 * [AndroidViewModel] for songs.
 */
class SongsViewModel(application: Application, private val songLoader: SongLoader) :
  AndroidViewModel(application) {

  private val songs: MutableLiveData<List<Song>> by lazy {
    MutableLiveData<List<Song>>().also {
      it.postValue(loadSongs())
    }
  }

  /**
   * Returns [LiveData] for [List] of [Song].
   */
  fun getSongs(): LiveData<List<Song>> = kotlin.run {
    songs
  }

  private fun loadSongs(): List<Song> =
    songLoader.getAllMusic(this.getApplication<Application>().contentResolver)
}

/**
 * [ViewModelProvider.NewInstanceFactory] for [SongsViewModel].
 */
class SongsViewModelFactory(
  private val application: Application,
  private val songLoader: SongLoader
) :
  ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    SongsViewModel(application, songLoader) as T
}
