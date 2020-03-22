package org.nooblabs.simplemusicplayer

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

/**
 * Main [Application] for initialzing global stuff.
 */
class MainApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    Logger.addLogAdapter(AndroidLogAdapter())
  }
}
