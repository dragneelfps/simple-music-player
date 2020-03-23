package org.nooblabs.simplemusicplayer.models

import android.net.Uri

/**
 * Represents a Song entity.
 */
data class Song(
  val id: Long,
  val title: String,
  val artist: String,
  val uri: Uri,
  val album: Album?
)
