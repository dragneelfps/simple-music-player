package org.nooblabs.simplemusicplayer.service

import android.content.Context
import android.media.MediaPlayer
import android.support.v4.media.session.MediaSessionCompat
import org.nooblabs.simplemusicplayer.callbacks.PlayerStatusListener
import org.nooblabs.simplemusicplayer.models.Song

class MediaController(private val context: Context, private val mediaSession: MediaSessionCompat) :
  Playback {

  private val mediaPlayer = MediaPlayer().apply {
    setOnCompletionListener(OnCompletionListener())
  }

  lateinit var playerStatusListener: PlayerStatusListener

  val queue = mutableListOf<Song>()
  var position: Int = -1

  val currentSong: Song
    get() = queue[position]

  override fun toggle() {
    if (mediaPlayer.isPlaying) {
      pause()
    } else {
      playCurrentSong()
    }
  }

  override fun play(song: Song) {
    clearQueue()
    addToQueue(song)
    playFromStart()
  }

  override fun addToQueue(vararg songs: Song) {
    songs.forEach { queue.add(it) }
  }

  override fun playFromStart() {
    position = 0
    playCurrentSong()
    mediaPlayer.audioSessionId
  }

  override fun playCurrentSong() {
    if (position < queue.size) {
      mediaPlayer.reset()
      mediaPlayer.setDataSource(context, currentSong.uri)
      mediaPlayer.prepare()
      startSong()
      setMetadata()
    } else {
      resetPosition()
    }
  }

  override fun resume() {
    if (!mediaPlayer.isPlaying) {
      startSong()
    }
  }

  override fun pause() {
    if (mediaPlayer.isPlaying) {
      mediaPlayer.pause()
    }
  }

  override fun playNextSong() {
    position++
    playCurrentSong()
  }

  fun release() {
    mediaPlayer.release()
  }

  private fun startSong() {
    mediaPlayer.start()
    playerStatusListener.onCurrentSongChanged(currentSong)
  }

  private fun setMetadata() {
    // TODO: 02/04/20 Add metadata to MediaSession
  }

  private fun resetPosition() {
    position = -1
  }

  override fun clearQueue() {
    queue.clear()
  }

  private inner class OnCompletionListener : MediaPlayer.OnCompletionListener {
    override fun onCompletion(mp: MediaPlayer) {
      playNextSong()
    }
  }
}