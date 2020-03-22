package org.nooblabs.simplemusicplayer.library

import android.content.ContentResolver
import android.database.Cursor
import android.provider.MediaStore.Audio.Media
import org.nooblabs.simplemusicplayer.models.Song

private val fields = arrayOf(Media._ID, Media.TITLE, Media.ARTIST)

/**
 * Contains functions to get songs.
 */
class MusicLibrary {

  /**
   * Returns all music.
   */
  fun getAllMusic(contentResolver: ContentResolver): List<Song> {
    return contentResolver.query(Media.EXTERNAL_CONTENT_URI, fields, null, null, null)
      ?.use { cursor ->
        val songs = arrayListOf<Song>()
        while (cursor.moveToNext()) {
          val song = Song(
            cursor.getColumnValue(Media._ID),
            cursor.getColumnValue(Media.TITLE),
            cursor.getColumnValue(Media.ARTIST)
          )
          songs.add(song)
        }
        songs
      } ?: emptyList()
  }

  private fun Cursor.getColumnValue(columnName: String): String =
    getString(getColumnIndex(columnName))
}
