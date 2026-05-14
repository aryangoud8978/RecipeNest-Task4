package com.example.recipenest.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.recipenest.R

object NotificationHelper {

    private const val CHANNEL_ID =
        "recipe_notifications"

    fun createNotificationChannel(
        context: Context
    ) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                CHANNEL_ID,
                "Recipe Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {

                description =
                    "RecipeNest daily recipe notifications"
            }

            val notificationManager =
                context.getSystemService(
                    NotificationManager::class.java
                )

            notificationManager.createNotificationChannel(
                channel
            )
        }
    }

    fun showNotification(
        context: Context
    ) {

        val builder = NotificationCompat.Builder(
            context,
            CHANNEL_ID
        )
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("RecipeNest 🍽️")
            .setContentText(
                "Check out today's delicious recipes!"
            )
            .setPriority(
                NotificationCompat.PRIORITY_DEFAULT
            )

        with(NotificationManagerCompat.from(context)) {

            notify(1, builder.build())
        }
    }
}