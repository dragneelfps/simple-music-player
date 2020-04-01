package org.nooblabs.simplemusicplayer.service

import org.nooblabs.simplemusicplayer.models.Song

interface Playback {
  fun toggle()
  fun play(song: Song)
  fun addToQueue(vararg songs: Song)
  fun playFromStart()
  fun playCurrentSong()
  fun resume()
  fun pause()
  fun playNextSong()
  fun clearQueue()
}