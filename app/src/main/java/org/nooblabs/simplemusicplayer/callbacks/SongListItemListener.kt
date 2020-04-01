package org.nooblabs.simplemusicplayer.callbacks

import org.nooblabs.simplemusicplayer.models.Song

/**
 * Callbacks for song item.
 */
interface SongListItemListener {
  /**
   * Callback when song item is clicked.
   */
  fun onSongClick(song: Song)
}
