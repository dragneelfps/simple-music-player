package org.nooblabs.simplemusicplayer.ui

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.florent37.runtimepermission.kotlin.askPermission
import kotlinx.android.synthetic.main.fragment_song_list.rv_song_list
import org.nooblabs.simplemusicplayer.R
import org.nooblabs.simplemusicplayer.loaders.SongLoader
import org.nooblabs.simplemusicplayer.ui.adaptors.rv.SongListAdaptor
import org.nooblabs.simplemusicplayer.viewmodels.CurrentPlayingViewModel
import org.nooblabs.simplemusicplayer.viewmodels.SongsViewModel
import org.nooblabs.simplemusicplayer.viewmodels.SongsViewModelFactory

/**
 * [Fragment] for list of songs.
 */
class SongListFragment : Fragment(R.layout.fragment_song_list) {

  private lateinit var songListAdaptor: SongListAdaptor
  private val songsViewModel: SongsViewModel by activityViewModels {
    SongsViewModelFactory(
      requireActivity().application,
      SongLoader()
    )
  }
  private val currentPlayingViewModel: CurrentPlayingViewModel by viewModels({ requireActivity() })

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    songListAdaptor = SongListAdaptor(currentPlayingViewModel)
    rv_song_list.adapter = songListAdaptor
    rv_song_list.layoutManager = LinearLayoutManager(requireContext())

    askPermission(Manifest.permission.READ_EXTERNAL_STORAGE) {
      songsViewModel.getSongs().observe(viewLifecycleOwner, Observer { songs ->
        songListAdaptor.loadSongs(songs)
      })
    }
  }
}
