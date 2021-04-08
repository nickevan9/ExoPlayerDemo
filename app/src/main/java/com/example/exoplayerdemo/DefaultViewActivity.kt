package com.example.exoplayerdemo

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.upstream.HttpDataSource
import kotlinx.android.synthetic.main.activity_default_view.*

class DefaultViewActivity : AppCompatActivity() {

    private lateinit var player: SimpleExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_default_view)

        initView()

        initEvent()

    }

    private fun initView() {
        player = ExoPlayerFactory.newSimpleInstance(this,
            DefaultTrackSelector(),
            DefaultLoadControl()
        )

        video_view.player = player

        player.playWhenReady = true

        val uri = Uri.parse("https://cdn.singsingenglish.com/new-sing/66c3d05eaa177e07d57465f948f0d8b934b7a7ba.mp4")
        val mediaSource = buildMediaSource(uri)
        player.prepare(mediaSource, false, true)
    }

    private fun initEvent(){
        /**
         * int STATE_IDLE = 1;
        int STATE_BUFFERING = 2;
        int STATE_READY = 3;
        int STATE_ENDED = 4;
         */
        player.addListener(object: Player.EventListener{
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                Log.e("ExoPlayer","playWhenReady: $playWhenReady  +$playbackState")
                when (playbackState){
                    Player.STATE_BUFFERING->
                        Toast.makeText(applicationContext,"加载中", Toast.LENGTH_LONG).show()
                    Player.STATE_READY->
                        Toast.makeText(applicationContext,"播放中", Toast.LENGTH_LONG).show()
                    Player.STATE_ENDED->
                        Toast.makeText(applicationContext,"播放完成", Toast.LENGTH_LONG).show()
                }
            }

            override fun onPlayerError(error: ExoPlaybackException) {
                Log.e("ExoPlayer","ExoPlaybackException: $error")

                error.let {
                    if (it!!.type == ExoPlaybackException.TYPE_SOURCE) {
                        val cause = it.sourceException
                        if (cause is HttpDataSource.HttpDataSourceException) {
                            Log.e("ExoPlayer","ExoPlaybackException: 网络异常")
                            // An HTTP error occurred.
                            // This is the request for which the error occurred.
                            val requestDataSpec = cause.dataSpec
                            // It's possible to find out more about the error both by casting and by
                            // querying the cause.
                            if (cause is HttpDataSource.InvalidResponseCodeException) {
                                // Cast to InvalidResponseCodeException and retrieve the response code,
                                // message and headers.
                            } else {
                                // Try calling httpError.getCause() to retrieve the underlying cause,
                                // although note that it may be null.
                            }
                        }
                    }
                }
            }
        })
    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        val dataSourceFactory = DefaultHttpDataSourceFactory("user-agent")
        val videoSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri)

//        val audioUri = Uri.parse(getString(R.string.media_url_mp3))
//        val audioSource = ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(audioUri)

        return ConcatenatingMediaSource(videoSource)
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}
