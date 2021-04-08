package com.example.exoplayerdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import kotlinx.android.synthetic.main.activity_main.*
import android.net.Uri
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.HttpDataSource
import com.google.android.exoplayer2.upstream.HttpDataSource.HttpDataSourceException
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.video.VideoListener


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_default.setOnClickListener {
            val intent = Intent(applicationContext,DefaultViewActivity::class.java)
            startActivity(intent)
        }

        btn_simple_diy.setOnClickListener {
            startActivity(Intent(applicationContext,SimpleDiyActivity::class.java))
        }

        btn_senior_diy.setOnClickListener {
            startActivity(Intent(applicationContext,SeniorDiyActivity::class.java))
        }

    }
}
