package org.nooblabs.simplemusicplayer.ui.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.nooblabs.simplemusicplayer.R

/**
 * Represents a song in queue.
 */
@Suppress("UndocumentedPublicProperty")
class QueueSongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  val title: TextView = itemView.findViewById(R.id.queue_song_title)
}
