package org.nooblabs.simplemusicplayer.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.afollestad.recyclical.datasource.emptyDataSourceTyped
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import kotlinx.android.synthetic.main.fragment_queue.rv_queue
import org.nooblabs.simplemusicplayer.R
import org.nooblabs.simplemusicplayer.models.Song
import org.nooblabs.simplemusicplayer.ui.viewholders.QueueSongViewHolder
import org.nooblabs.simplemusicplayer.viewmodels.CurrentPlayingViewModel

/**
 * Displays queue.
 */
class QueueFragment : Fragment(R.layout.fragment_queue) {

  private val currentPlayingViewModel: CurrentPlayingViewModel by viewModels({ requireActivity() })

  private val songsDataSource = emptyDataSourceTyped<Song>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    currentPlayingViewModel.getQueue().observe(viewLifecycleOwner, Observer { queue ->
      songsDataSource.clear()
      songsDataSource.addAll(queue)
    })
    rv_queue.setup {
      withDataSource(songsDataSource)
      withItem<Song, QueueSongViewHolder>(R.layout.item_queue_song) {
        onBind(::QueueSongViewHolder) { _, song ->
          title.text = song.title
        }
      }
    }
  }
}
