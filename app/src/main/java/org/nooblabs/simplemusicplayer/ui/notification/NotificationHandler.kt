package org.nooblabs.simplemusicplayer.ui.notification

import android.content.Context
import androidx.core.app.NotificationCompat

/**
 * todo:
 * complete this after moving to media controller.
 */
class NotificationHandler {

  /**
   * creates player notification.
   */
  fun createNotification(context: Context) {
    NotificationCompat.Builder(context, CHANNEL_ID)
      .build()
  }

  companion object {
    private const val CHANNEL_ID = "asdasdasd"
  }
}
