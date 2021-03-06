package org.nooblabs.simplemusicplayer.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_song_list.rv_song_list
import org.nooblabs.simplemusicplayer.R
import org.nooblabs.simplemusicplayer.callbacks.SongListItemListener
import org.nooblabs.simplemusicplayer.models.Song
import org.nooblabs.simplemusicplayer.ui.adaptors.rv.SongListAdaptor

/**
 * [Fragment] for list of songs.
 */
class SongListFragment : Fragment(R.layout.fragment_song_list) {

  private lateinit var songListAdaptor: SongListAdaptor

  lateinit var songListItemListener: SongListItemListener

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    songListAdaptor = SongListAdaptor(songListItemListener)
    rv_song_list.adapter = songListAdaptor
    rv_song_list.layoutManager = LinearLayoutManager(requireContext())
  }

  fun loadSongs(songs: List<Song>) {
    songListAdaptor.loadSongs(songs)
  }
}
