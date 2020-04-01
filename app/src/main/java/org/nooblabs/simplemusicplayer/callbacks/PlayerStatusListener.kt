package org.nooblabs.simplemusicplayer.callbacks

import org.nooblabs.simplemusicplayer.models.Song

/**
 * Callbacks for Current playing song.
 */
interface PlayerStatusListener {

  /**
   * Callback when current song is changed.
   */
  fun onCurrentSongChanged(song: Song)
}
