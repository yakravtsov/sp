package com.example.yicameraprototype.ui

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.rtsp.RtspMediaSource
import com.example.yicameraprototype.domain.LiveState

class LiveStreamController(context: Context) {
    private var stateListener: ((LiveState, String?) -> Unit)? = null

    private val liveMediaItem = MediaItem.fromUri("rtsp://192.168.42.1/live")
    private val rtspMediaSourceFactory = RtspMediaSource.Factory()

    private val lowLatencyLoadControl = DefaultLoadControl.Builder()
        .setBufferDurationsMs(
            /* minBufferMs = */ 2000,
            /* maxBufferMs = */ 5000,
            /* bufferForPlaybackMs = */ 500,
            /* bufferForPlaybackAfterRebufferMs = */ 1000
        )
        .build()

    val player: ExoPlayer = ExoPlayer.Builder(context)
        .setLoadControl(lowLatencyLoadControl)
        .build().apply {
        addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                when (playbackState) {
                    Player.STATE_BUFFERING -> stateListener?.invoke(LiveState.Buffering, null)
                    Player.STATE_READY -> {
                        if (playWhenReady) {
                            stateListener?.invoke(LiveState.Playing, null)
                        }
                    }
                    Player.STATE_ENDED,
                    Player.STATE_IDLE -> stateListener?.invoke(LiveState.Stopped, null)
                }
            }

            override fun onPlayerError(error: PlaybackException) {
                stateListener?.invoke(LiveState.Error, error.message)
            }
        })
    }

    fun setStateListener(listener: (LiveState, String?) -> Unit) {
        stateListener = listener
    }

    fun start() {
        runCatching {
            player.stop()
            player.clearMediaItems()
            player.setMediaSource(rtspMediaSourceFactory.createMediaSource(liveMediaItem))
            player.prepare()
            player.playWhenReady = true
        }.onFailure { error ->
            stateListener?.invoke(LiveState.Error, error.message ?: "Live stream start failed")
        }
    }

    fun stop(reason: String = "manual") {
        player.stop()
        player.clearMediaItems()
        stateListener?.invoke(LiveState.Stopped, reason)
    }

    fun release() {
        player.release()
    }
}
