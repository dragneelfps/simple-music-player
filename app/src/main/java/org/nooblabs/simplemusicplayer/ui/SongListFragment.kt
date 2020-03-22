package org.nooblabs.simplemusicplayer.ui

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.github.florent37.runtimepermission.kotlin.askPermission
import com.orhanobut.logger.Logger
import org.nooblabs.simplemusicplayer.R
import org.nooblabs.simplemusicplayer.loaders.SongLoader
import org.nooblabs.simplemusicplayer.viewmodels.SongsViewModel
import org.nooblabs.simplemusicplayer.viewmodels.SongsViewModelFactory

/**
 * [Fragment] for list of songs.
 */
class SongListFragment : Fragment() {

  private val songsViewModel: SongsViewModel by activityViewModels {
    SongsViewModelFactory(
      requireActivity().application,
      SongLoader()
    )
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_song_list, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    askPermission(Manifest.permission.READ_EXTERNAL_STORAGE) {
      songsViewModel.getSongs().observe(viewLifecycleOwner, Observer { songs ->
        Logger.d(songs)
      })
    }
  }
}
