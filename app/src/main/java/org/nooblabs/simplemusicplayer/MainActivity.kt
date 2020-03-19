package org.nooblabs.simplemusicplayer

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.nooblabs.simplemusicplayer.library.getAllMusic

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val songs = contentResolver.getAllMusic()
        Log.d(TAG, "onCreate: $songs")
    }

    companion object {
        private val TAG = MainActivity::class.simpleName
    }
}