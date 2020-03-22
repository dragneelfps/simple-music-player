package org.nooblabs.simplemusicplayer.ui.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.nooblabs.simplemusicplayer.R

/**
 * Represents a song.
 */
@Suppress("UndocumentedPublicProperty")
class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  val title: TextView = itemView.findViewById(R.id.song_title)
  val artist: TextView = itemView.findViewById(R.id.song_artist)
}
