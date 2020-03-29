@file:Suppress("MagicNumber")

package org.nooblabs.simplemusicplayer.loaders

import android.content.ContentResolver
import android.content.ContentUris
import android.net.Uri
import android.provider.BaseColumns
import android.provider.MediaStore.Audio.AudioColumns
import android.provider.MediaStore.Audio.Media
import org.nooblabs.simplemusicplayer.models.Album
import org.nooblabs.simplemusicplayer.models.Song

private const val MUSIC_SELECTION = "${AudioColumns.IS_MUSIC}=1 AND ${AudioColumns.TITLE}!=''"
private val fields = arrayOf(
  BaseColumns._ID,
  AudioColumns.TITLE,
  AudioColumns.ARTIST,
  AudioColumns.ALBUM_ID,
  AudioColumns.ALBUM
)

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
            id = cursor.getLong(0),
            title = cursor.getString(1),
            artist = cursor.getString(2),
            uri = ContentUris.withAppendedId(
              Media.EXTERNAL_CONTENT_URI,
              cursor.getLong(0)
            ),
            album = Album(
              id = cursor.getLong(3),
              art = ContentUris.withAppendedId(
                Uri.parse("content://media/external/audio/albumart"),
                cursor.getLong(3)
              ),
              name = cursor.getString(4)
            )
          )
          songs.add(song)
        }
        songs
      } ?: emptyList()
  }
}
