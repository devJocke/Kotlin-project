package com.example.jocke.kotlin

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.extractor.mp4.Mp4Extractor
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.ui.SimpleExoPlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DataSpec
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import kotlinx.android.synthetic.main.splash_screen_activity.*

class SplashActivity : AppCompatActivity(), Player.EventListener {

    private lateinit var player: SimpleExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen_activity)
        val playerView: SimpleExoPlayerView = findViewById(R.id.player_view)

        hideSystemUI()
        setupLoginButton()
        createExoPlayer(playerView)
    }

    private fun setupLoginButton() {

        //TODO Validate if false don't speed up or login
        login.setOnClickListener({
            if (player.playbackParameters.speed == 1f) {
                player.playbackParameters = PlaybackParameters(5f, 1f)
                Handler().postDelayed({
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }, 500)
            } else {
                player.playbackParameters = PlaybackParameters(1f, 1f)
            }
        })
    }


    private fun createExoPlayer(playerView: SimpleExoPlayerView) {
        // 1. Create a default TrackSelector
        val bandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
        val dataSpec = DataSpec(RawResourceDataSource.buildRawResourceUri(R.raw.star_effect))
        val rawResourceDataSource = RawResourceDataSource(this)
        rawResourceDataSource.open(dataSpec)
        val factory = DataSource.Factory { rawResourceDataSource }
        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector)
        // This is the MediaSource representing the media to be played.
        val videoSource = ExtractorMediaSource(rawResourceDataSource.uri, factory, Mp4Extractor.FACTORY, null, null)

        exoPlayerViewSettings(player, playerView, videoSource)
    }

    private fun exoPlayerViewSettings(player: SimpleExoPlayer, playerView: SimpleExoPlayerView, videoSource: ExtractorMediaSource) {
        playerView.player = player
        playerView.keepScreenOn = true
        playerView.useController = false
        playerView.requestFocus()
        player.repeatMode = Player.REPEAT_MODE_ALL
        player.addListener(this)
        player.prepare(videoSource)
        player.playWhenReady = true
    }

    override fun onResume() {
        super.onResume()
        uiChangeListener()
    }

    override fun onPause() {
        super.onPause()
        player.removeListener(this)
    }

    // This snippet hides the system bars.
    private fun hideSystemUI() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_IMMERSIVE
    }

    // This snippet shows the system bars. It does this by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

    override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters?) {}

    override fun onTracksChanged(trackGroups: TrackGroupArray?, trackSelections: TrackSelectionArray?) {}

    override fun onPlayerError(error: ExoPlaybackException) {
        Log.e("Error", error.message)
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        when (playbackState) {
            Player.STATE_BUFFERING -> {
            }
            Player.STATE_IDLE -> {
            }
            Player.STATE_READY -> {
            }
            Player.STATE_ENDED -> {
            }
        }
    }

    override fun onLoadingChanged(isLoading: Boolean) {
    }

    override fun onPositionDiscontinuity() {
    }

    override fun onRepeatModeChanged(repeatMode: Int) {
    }

    override fun onTimelineChanged(timeline: Timeline?, manifest: Any?) {
    }

    private fun uiChangeListener() {
        val decorView = window.decorView

        decorView.setOnSystemUiVisibilityChangeListener { visibility ->
            if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            }
        }
    }
}