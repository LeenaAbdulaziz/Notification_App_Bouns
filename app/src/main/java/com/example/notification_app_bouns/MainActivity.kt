package com.example.notification_app_bouns

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    val channelId = "notification"
    val description = "Notification App"
    lateinit var timer: TextView
    lateinit var btnstart: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        timer=findViewById(R.id.textView)
        btnstart=findViewById(R.id.btnStart)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        var time = object : CountDownTimer(8000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timer.text = "Time: ${millisUntilFinished / 1000}"

            }

            override fun onFinish() {
                timer.text = "Time: --"
                notify(notificationManager)
            }
        }
        btnstart.setOnClickListener{
            time.start()
        }



    }
    fun notify(n:NotificationManager){
        var builder: Notification.Builder
        val intent = Intent(this, new_page::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var notificationChannel = NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
            n.createNotificationChannel(notificationChannel)

            builder= Notification.Builder(this,channelId)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentIntent(pendingIntent)
                .setContentTitle("Egg cooking counter")
                .setContentText("ready")
        } else {
            builder= Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentIntent(pendingIntent)
                .setContentTitle("Egg cooking counter")
                .setContentText("ready")

        }
        n.notify(1234, builder.build())
    }
}