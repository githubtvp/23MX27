package com.example.g2labtest

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings
import android.widget.Toast

class MyService : Service() {
    private lateinit var player : MediaPlayer

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        player = MediaPlayer.create(this,Settings.System.DEFAULT_RINGTONE_URI)
        player.isLooping = true
        player.start()
        Toast.makeText(this,"Service Started",Toast.LENGTH_LONG).show()
        return START_STICKY
    }
    override fun onDestroy(){
        super.onDestroy()
        player.stop()
        Toast.makeText(this,"Service Stopped",Toast.LENGTH_LONG).show()
    }
}
