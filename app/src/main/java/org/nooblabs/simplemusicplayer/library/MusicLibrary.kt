package org.nooblabs.simplemusicplayer.library

import android.content.ContentResolver
import android.provider.MediaStore.Audio.Media

/**
 * Returns all music.
 */
fun ContentResolver.getAllMusic(): List<Map<String, String?>> {
  return query(Media.EXTERNAL_CONTENT_URI, null, null, null, null)?.use { cursor ->
    val songs = arrayListOf<Map<String, String?>>()
    while (cursor.moveToNext()) {
      val song = hashMapOf<String, String?>()
      for (i in 0 until cursor.columnCount) {
        val attributeName = cursor.getColumnName(i)
        val attributeValue = cursor.getString(i)
        song[attributeName] = attributeValue
      }
      songs.add(song)
    }
    songs
  } ?: emptyList()
}
