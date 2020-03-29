package org.nooblabs.simplemusicplayer.models

import android.net.Uri

/**
 * Represents an album entity.
 */
data class Album(val id: Long, val art: Uri, val name: String)
