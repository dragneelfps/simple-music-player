package org.nooblabs.simplemusicplayer.loaders

import android.content.ContentResolver
import android.database.Cursor
import android.provider.BaseColumns
import android.provider.MediaStore.Audio.AudioColumns
import android.provider.MediaStore.Audio.Media
import org.nooblabs.simplemusicplayer.models.Song

private const val MUSIC_SELECTION = "${AudioColumns.IS_MUSIC}=1 AND ${AudioColumns.TITLE}!=''"
private val fields = arrayOf(BaseColumns._ID, AudioColumns.TITLE, AudioColumns.ARTIST)

/**
 * Contains functions to get songs.
 */
class SongLoader {

  /**
   * Returns all music.
   */
  fun getAllMusic(contentResolver: ContentResolver): List<Song> {
    return contentResolver.query(Media.EXTERNAL_CONTENT_URI, fields, MUSIC_SELECTION, null, null)
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
