package org.nooblabs.simplemusicplayer.ui.adaptors

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.nooblabs.simplemusicplayer.ui.PlayingFragment
import org.nooblabs.simplemusicplayer.ui.QueueFragment

/**
 * [FragmentStateAdapter] for player.
 */
class PlayerViewPagerAdaptor(fa: FragmentManager) :
  FragmentPagerAdapter(fa, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

  override fun getItem(position: Int): Fragment = when (position) {
    0 -> PlayingFragment()
    1 -> QueueFragment()
    else -> throw IllegalStateException()
  }

  override fun getCount(): Int = 2
}
