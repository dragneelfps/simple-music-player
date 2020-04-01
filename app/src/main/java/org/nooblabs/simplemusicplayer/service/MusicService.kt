package org.nooblabs.simplemusicplayer.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat

class MusicService : Service() {

  private val binder = MusicServiceBinder(this)

  private lateinit var mediaSession: MediaSessionCompat
  lateinit var mediaController: MediaController
  private val mediaSessionCallback = MediaSessionCallback()

  override fun onCreate() {
    super.onCreate()
    mediaSession = MediaSessionCompat(applicationContext, "SimpleMusicPayer")
    mediaSession.setCallback(mediaSessionCallback)
    mediaSession.isActive = true

    mediaController = MediaController(applicationContext, mediaSession)
  }

  override fun onDestroy() {
    super.onDestroy()
    mediaSession.isActive = false
    quit()
    releaseResources()
  }

  override fun onBind(intent: Intent): IBinder = binder

  inner class MediaSessionCallback : MediaSessionCompat.Callback() {

    override fun onPlay() {
      mediaController.playCurrentSong()
    }

    override fun onPause() {
      mediaController.pause()
    }

    override fun onSkipToNext() {
      mediaController.playNextSong()
    }

    override fun onStop() {
      quit()
    }

  }

  private fun releaseResources() {
    mediaController.release()
    mediaSession.release()
  }

  private fun quit() {
    mediaController.pause()
    stopSelf()
  }

}

/**
 * [Binder] for [MusicService].
 */
class MusicServiceBinder(private val musicService: MusicService) : Binder() {
  fun getService(): MusicService = musicService
}