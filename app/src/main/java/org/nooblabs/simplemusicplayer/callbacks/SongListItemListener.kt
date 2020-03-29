package org.nooblabs.simplemusicplayer.callbacks

/**
 * Callbacks for song item.
 */
interface SongListItemListener {
  /**
   * Callback when song item is clicked.
   */
  fun onSongClick(index: Int)
}
