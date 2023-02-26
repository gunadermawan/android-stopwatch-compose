package com.gunder.stopwatch.service

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import com.gunder.stopwatch.MainActivity
import com.gunder.stopwatch.utils.Constants.CANCEL_REQUEST_CODE
import com.gunder.stopwatch.utils.Constants.CLICK_REQUEST_CODE
import com.gunder.stopwatch.utils.Constants.RESUME_REQUEST_CODE
import com.gunder.stopwatch.utils.Constants.STOPWATCH_STATE
import com.gunder.stopwatch.utils.Constants.STOP_REQUEST_CODE

@ExperimentalAnimationApi
object ServiceHelper {
    private const val flag = PendingIntent.FLAG_IMMUTABLE

    fun clickPendingIntent(context: Context): PendingIntent {
        val clickIntent = Intent(context, MainActivity::class.java).apply {
            putExtra(STOPWATCH_STATE, StopwatchState.Started.name)
        }
        return PendingIntent.getActivity(context, CLICK_REQUEST_CODE, clickIntent, flag)
    }

    @OptIn(ExperimentalAnimationApi::class)
    fun stopPendingIntent(context: Context): PendingIntent {
        val stopIntent = Intent(context, StopwatchService::class.java).apply {
            putExtra(STOPWATCH_STATE, StopwatchState.Stopped.name)
        }
        return PendingIntent.getService(
            context, STOP_REQUEST_CODE, stopIntent, flag
        )
    }

    @OptIn(ExperimentalAnimationApi::class)
    fun resumePendingIntent(context: Context): PendingIntent {
        val resumeIntent = Intent(context, StopwatchService::class.java).apply {
            putExtra(STOPWATCH_STATE, StopwatchState.Started.name)
        }
        return PendingIntent.getService(
            context, RESUME_REQUEST_CODE, resumeIntent, flag
        )
    }

    @OptIn(ExperimentalAnimationApi::class)
    fun cancelPendingIntent(context: Context): PendingIntent {
        val cancelIntent = Intent(context, StopwatchService::class.java).apply {
            putExtra(STOPWATCH_STATE, StopwatchState.Canceled.name)
        }
        return PendingIntent.getService(
            context, CANCEL_REQUEST_CODE, cancelIntent, flag
        )
    }

    @OptIn(ExperimentalAnimationApi::class)
    fun triggerForegroundService(context: Context, action: String) {
        Intent(context, StopwatchService::class.java).apply {
            this.action = action
            context.startService(this)
        }
    }
}