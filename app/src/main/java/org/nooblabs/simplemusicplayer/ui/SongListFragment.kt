package org.nooblabs.simplemusicplayer.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import org.nooblabs.simplemusicplayer.R
import org.nooblabs.simplemusicplayer.library.MusicLibrary
import org.nooblabs.simplemusicplayer.viewmodels.SongsViewModel
import org.nooblabs.simplemusicplayer.viewmodels.SongsViewModelFactory

/**
 * [Fragment] for list of songs.
 */
class SongListFragment : Fragment() {

  private val songsViewModel: SongsViewModel by activityViewModels {
    SongsViewModelFactory(
      requireActivity().application,
      MusicLibrary()
    )
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_song_list, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    songsViewModel.getSongs().observe(viewLifecycleOwner, Observer { songs ->
      Log.d("asd", "onViewCreated: $songs")
    })
  }
}
