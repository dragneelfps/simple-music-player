@file:Suppress("UndocumentedPublicProperty")

package org.nooblabs.simplemusicplayer.ui.adaptors.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.nooblabs.simplemusicplayer.R
import org.nooblabs.simplemusicplayer.callbacks.SongListItemListener
import org.nooblabs.simplemusicplayer.models.Song


/**
 * Adaptor for song list.
 */
class SongListAdaptor(private var songListItemListener: SongListItemListener) :
  RecyclerView.Adapter<SongListAdaptor.SongViewHolder>() {

  init {
    setHasStableIds(true)
  }

  private val songList: MutableList<Song> = ArrayList()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder =
    SongViewHolder(
      LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
    )

  override fun getItemCount(): Int = songList.size

  override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
    holder.apply {
      val song = songList[position]
      title.text = song.title
      artist.text = song.artist
      album.text = song.album?.name
      Glide
        .with(itemView)
        .load(song.album?.art)
        .placeholder(R.drawable.ic_default_album_img)
        .into(albumArt)
      menu.setOnClickListener { v ->
        val popupMenu = PopupMenu(itemView.context, v)
        popupMenu.menuInflater.inflate(R.menu.song_popup, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
          when (item.itemId) {
            else -> false
          }
        }
        popupMenu.show()
      }
      itemView.setOnClickListener { songListItemListener.onSongClick(position) }
    }
  }

  override fun getItemId(position: Int): Long = songList[position].id

  /**
   * Load songs in the adaptor.
   */
  fun loadSongs(songs: List<Song>) {
    songList.clear()
    songList.addAll(songs)
    notifyDataSetChanged()
  }

  /**
   * Represents a song.
   */
  class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.song_title)
    val albumArt: ImageView = itemView.findViewById(
      R.id.song_album_art
    )
    val artist: TextView = itemView.findViewById(R.id.song_artist)
    val album: TextView = itemView.findViewById(R.id.song_album)
    val menu: View = itemView.findViewById(R.id.song_menu)
  }
}

