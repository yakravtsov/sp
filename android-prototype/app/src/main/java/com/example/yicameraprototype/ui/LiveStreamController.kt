package com.example.yicameraprototype.ui

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

class LiveStreamController(context: Context) {
    val player: ExoPlayer = ExoPlayer.Builder(context).build()

    fun start() {
        player.setMediaItem(MediaItem.fromUri("rtsp://192.168.42.1/live"))
        player.prepare()
        player.playWhenReady = true
    }

    fun stop() {
        player.stop()
        player.clearMediaItems()
    }

    fun release() {
        player.release()
    }
}
