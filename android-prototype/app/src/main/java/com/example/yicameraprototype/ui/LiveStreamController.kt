package com.example.yicameraprototype.ui

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.rtsp.RtspMediaSource
import com.example.yicameraprototype.domain.LiveState

class LiveStreamController(context: Context) {
    private var stateListener: ((LiveState, String?) -> Unit)? = null

    private val liveMediaItem = MediaItem.fromUri("rtsp://192.168.42.1/live")
    private val rtspMediaSourceFactory = RtspMediaSource.Factory()
        .setForceUseRtpTcp(true)

    val player: ExoPlayer = ExoPlayer.Builder(context).build().apply {
        addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                when (playbackState) {
                    Player.STATE_BUFFERING -> stateListener?.invoke(LiveState.Buffering, null)
                    Player.STATE_READY -> {
                        if (playWhenReady) stateListener?.invoke(LiveState.Playing, null)
                    }

                    Player.STATE_ENDED, Player.STATE_IDLE -> stateListener?.invoke(LiveState.Stopped, null)
                }
            }

            override fun onPlayerError(error: androidx.media3.common.PlaybackException) {
                stateListener?.invoke(LiveState.Error, error.message)
            }
        })
    }

    fun setStateListener(listener: (LiveState, String?) -> Unit) {
        stateListener = listener
    }

    fun start() {
        runCatching {
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
