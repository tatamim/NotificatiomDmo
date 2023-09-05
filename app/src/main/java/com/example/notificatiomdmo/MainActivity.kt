package com.example.notificatiomdmo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ClipDescription
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.NotificationCompat
import com.example.notificatiomdmo.ui.theme.NotificatiomDmoTheme

class MainActivity : ComponentActivity() {
    private val channelId = "com.example.notificatiomdmo.channel1"
    private var notificationManager: NotificationManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        var button = findViewById<Button>(R.id.button)

        createNotificationChannel(channelId, "DemoChannel", "this is a demo")

        button.setOnClickListener {
            displayNotification()
        }

    }

    private fun displayNotification() {
        val notificationId = 45
        val notification = NotificationCompat.Builder(this@MainActivity, channelId)
            .setContentTitle("Demo Title")
            .setContentText("This is a demo notification")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        notificationManager?.notify(notificationId,notification)
    }

    private fun createNotificationChannel(id: String, name: String, channelDescription: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id, name, importance).apply {
                description = channelDescription
            }
            notificationManager?.createNotificationChannel(channel)
        }
    }
}