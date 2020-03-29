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

  private val queueLiveData = MutableLiveData<LinkedList<Song>>(LinkedList())

  private val currentSongLiveData = MutableLiveData<Song>()

  /**
   * Returns [LiveData] for current [Song].
   */
  fun getCurrentSong(): LiveData<Song> = currentSongLiveData

  /**
   * Returns [LiveData] for queue.
   */
  fun getQueue(): LiveData<LinkedList<Song>> = queueLiveData

  /**
   * Move next in [queueLiveData].
   */
  fun playNext() {
    queueLiveData.value?.let { queue ->
      val next = queue.poll() ?: return
      currentSongLiveData.postValue(next)
      queueLiveData.postValue(queue)
    }
  }

  /**
   * Adds [song] to the [queueLiveData].
   */
  fun addSongToQueue(song: Song) {
    queueLiveData.value?.let { queue ->
      if (queue.isEmpty()) {
        currentSongLiveData.postValue(song)
      }
      queue.add(song)
      queueLiveData.postValue(queue)
    }
  }
}
