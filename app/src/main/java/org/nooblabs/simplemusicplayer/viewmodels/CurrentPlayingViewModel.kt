package org.nooblabs.simplemusicplayer.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.nooblabs.simplemusicplayer.models.Song
import java.util.LinkedList

/**
 * Contains state of current playing song and queue.
 */
class CurrentPlayingViewModel : ViewModel() {

  private val queue = LinkedList<Song>()

  private val currentSongLiveData = MutableLiveData<Song>()

  /**
   * Returns [LiveData] for current [Song].
   */
  fun getCurrentSong(): LiveData<Song> = currentSongLiveData

  /**
   * Move next in [queue].
   */
  fun playNext() {
    val next = queue.poll() ?: return
    currentSongLiveData.postValue(next)
  }

  /**
   * Adds [song] to the [queue].
   */
  fun addSongToQueue(song: Song) {
    queue.add(song)
  }
}
