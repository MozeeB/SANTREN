package com.cikup.santren.presentation.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.cikup.santren.R.anim.*
import com.cikup.santren.presentation.ui.dashboard.MainActivity
import com.cikup.santren.presentation.ui.login.LoginActivity

import com.cikup.santren.presentation.ui.register.RegisterActivity

fun navigateToRegister(context: Context) {
    if (context != null && context is Activity) {
        val activity = context
        val flags = context.flags(Intent.FLAG_ACTIVITY_NEW_TASK, Intent.FLAG_ACTIVITY_CLEAR_TOP)
        activity.start<RegisterActivity>(flags, right_in, left_out)
    }
}

fun navigateToDashboard(context: Context){
    if (context != null && context is Activity) {
        val activity = context
        val flags = context.flags(Intent.FLAG_ACTIVITY_NEW_TASK, Intent.FLAG_ACTIVITY_CLEAR_TOP)
        activity.start<MainActivity>(flags, right_in, left_out)
    }
}

fun navigateToLogin(context: Context){
    if (context != null && context is Activity) {
        val activity = context
        val flags = context.flags(Intent.FLAG_ACTIVITY_NEW_TASK, Intent.FLAG_ACTIVITY_CLEAR_TOP)
        activity.start<LoginActivity>(flags, right_in, left_out)
    }
}

fun backToLogin(context: Context) {
    if (context != null && context is Activity) {
        val activity = context
        val flags = context.flags(Intent.FLAG_ACTIVITY_NEW_TASK, Intent.FLAG_ACTIVITY_CLEAR_TOP)
        activity.start<LoginActivity>(flags, left_in, right_out)
    }
}
