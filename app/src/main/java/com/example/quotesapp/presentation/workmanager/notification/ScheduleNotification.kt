package com.example.quotesapp.presentation.workmanager.notification

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScheduleNotification @Inject constructor(@ApplicationContext private val context: Context)  {

    fun scheduleNotification(){

        val workRequest = PeriodicWorkRequestBuilder<NotificationWorkManager>(48, TimeUnit.HOURS)
            .setConstraints(
                Constraints.Builder()
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                .build())
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "quotes_notification",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest)

    }

}