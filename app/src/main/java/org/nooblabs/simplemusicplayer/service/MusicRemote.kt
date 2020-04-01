package org.nooblabs.simplemusicplayer.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.fragment.app.FragmentActivity
import org.nooblabs.simplemusicplayer.callbacks.PlayerStatusListener
import org.nooblabs.simplemusicplayer.models.Song

object MusicRemote : Playback {

  private var mediaController: MediaController? = null
  var musicService: MusicService? = null
    private set
  lateinit var playerStatusListener: PlayerStatusListener

  private val musicServiceConnection = object : ServiceConnection {
    override fun onServiceConnected(name: ComponentName, binder: IBinder) {
      musicService = (binder as MusicServiceBinder).getService()
      mediaController = musicService?.mediaController?.apply {
        playerStatusListener = this@MusicRemote.playerStatusListener
      }
    }

    override fun onServiceDisconnected(name: ComponentName?) {
      musicService = null
      mediaController = null
    }
  }

  fun startAndBindToService(activity: FragmentActivity) {
    Intent(activity, MusicService::class.java).let { intent ->
      activity.bindService(intent, musicServiceConnection, Context.BIND_AUTO_CREATE)
    }
  }

  fun stopAndUnbindService(activity: FragmentActivity) {
    activity.unbindService(musicServiceConnection)
  }

  override fun toggle() {
    mediaController?.toggle()
  }

  override fun play(song: Song) {
    mediaController?.play(song)
  }

  override fun addToQueue(vararg songs: Song) {
    mediaController?.addToQueue(*songs)
  }

  override fun playFromStart() {
    mediaController?.playFromStart()
  }

  override fun playCurrentSong() {
    mediaController?.playCurrentSong()
  }

  override fun resume() {
    mediaController?.resume()
  }

  override fun pause() {
    mediaController?.pause()
  }

  override fun playNextSong() {
    mediaController?.playNextSong()
  }

  override fun clearQueue() {
    mediaController?.clearQueue()
  }
}