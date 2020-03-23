package org.nooblabs.simplemusicplayer.ui.adaptors

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.nooblabs.simplemusicplayer.ui.PlayingFragment

/**
 * [FragmentStateAdapter] for player.
 */
class PlayerViewPagerAdaptor(fa: FragmentActivity) : FragmentStateAdapter(fa) {
  override fun getItemCount() = 10

  override fun createFragment(position: Int) = when (position) {
    0 -> PlayingFragment()
    else -> PlayingFragment()
  }
}
