package com.example.exoplayerdemo

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.example.exoplayerdemo.custom.MyPlayerControlView
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.DefaultTimeBar
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import kotlinx.android.synthetic.main.activity_senior_diy.*

class SeniorDiyActivity : AppCompatActivity() {

    private lateinit var player: SimpleExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_senior_diy)

        player = ExoPlayerFactory.newSimpleInstance(this,
            DefaultTrackSelector(),
            DefaultLoadControl()
        )
        player.playWhenReady = true

        video_view.player = player

        val uri = Uri.parse("https://cdn.singsingenglish.com/new-sing/66c3d05eaa177e07d57465f948f0d8b934b7a7ba.mp4")
        val dataSourceFactory = DefaultHttpDataSourceFactory("user-agent")
        val videoSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
        player.prepare(videoSource)

//        video_view.controller?.setCustomEventListener(object :
//            MyPlayerControlView.CustomEventListener{
//            override fun onBroadCastClick() {
//                Toast.makeText(this@SeniorDiyActivity,"其他事件",Toast.LENGTH_LONG).show()
//            }
//
//            override fun onBackClick() {
//                this@SeniorDiyActivity.finish()
//            }
//
//        })
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}
