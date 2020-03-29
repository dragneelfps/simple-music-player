package org.nooblabs.simplemusicplayer.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_playing.playing_album_art
import kotlinx.android.synthetic.main.fragment_playing.playing_title
import org.nooblabs.simplemusicplayer.R
import org.nooblabs.simplemusicplayer.viewmodels.CurrentPlayingViewModel

/**
 * Shows the current playing song with UI controls.
 */
class PlayingFragment : Fragment(R.layout.fragment_playing) {

  private val currentPlayingViewModel: CurrentPlayingViewModel by viewModels({ requireActivity() })

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    currentPlayingViewModel.getCurrentSong().observe(viewLifecycleOwner, Observer { song ->
      Glide
        .with(this)
        .load(song.album?.art)
        .placeholder(R.drawable.ic_default_album_img)
        .into(playing_album_art)
      playing_title.text = song.title
    })
  }
}
