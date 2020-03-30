package org.nooblabs.simplemusicplayer.ui

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.florent37.runtimepermission.kotlin.askPermission
import kotlinx.android.synthetic.main.fragment_song_list.rv_song_list
import org.nooblabs.simplemusicplayer.R
import org.nooblabs.simplemusicplayer.callbacks.SelectedSongListener
import org.nooblabs.simplemusicplayer.callbacks.SongListItemListener
import org.nooblabs.simplemusicplayer.ui.adaptors.rv.SongListAdaptor
import org.nooblabs.simplemusicplayer.viewmodels.SongsViewModel

/**
 * [Fragment] for list of songs.
 */
class SongListFragment(
  private val songsViewModel: SongsViewModel,
  private val selectedSongListener: SelectedSongListener
) :
  Fragment(R.layout.fragment_song_list),
  SongListItemListener {

  private lateinit var songListAdaptor: SongListAdaptor

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    songListAdaptor = SongListAdaptor(this as SongListItemListener)
    rv_song_list.adapter = songListAdaptor
    rv_song_list.layoutManager = LinearLayoutManager(requireContext())

    askPermission(Manifest.permission.READ_EXTERNAL_STORAGE) {
      songsViewModel.loadSongs(requireActivity().contentResolver)
      songsViewModel.getSongs().observe(viewLifecycleOwner, Observer { songs ->
        songListAdaptor.loadSongs(songs)
      })
    }

    songsViewModel.currentSong().observe(viewLifecycleOwner, Observer { song ->
      selectedSongListener.onCurrentSongChanged(song)
    })
  }

  override fun onSongClick(index: Int) {
    songsViewModel.playSong(index)
  }
}
