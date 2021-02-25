package com.cikup.santren.presentation.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.cikup.santren.R.anim.*
import com.cikup.santren.presentation.ui.absent.AbsentActivity
import com.cikup.santren.presentation.ui.dashboard.MainActivity
import com.cikup.santren.presentation.ui.event.EventActivity
import com.cikup.santren.presentation.ui.information.DetailInfoActivity
import com.cikup.santren.presentation.ui.information.InformationActivity
import com.cikup.santren.presentation.ui.login.LoginActivity

import com.cikup.santren.presentation.ui.register.RegisterActivity
import com.cikup.santren.presentation.ui.report.ReportActivity

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
fun navigateToInformation(context: Context){
    if (context != null && context is Activity) {
        val activity = context
        val flags = context.flags(Intent.FLAG_ACTIVITY_NEW_TASK, Intent.FLAG_ACTIVITY_CLEAR_TOP)
        activity.start<InformationActivity>(flags, right_in, left_out)
    }
}
fun navigateToDetailInfo(context: Context, bundle: Bundle){
    if (context != null && context is Activity) {
        val activity = context
        val flags = context.flags(Intent.FLAG_ACTIVITY_NEW_TASK, Intent.FLAG_ACTIVITY_CLEAR_TOP)
        activity.start<DetailInfoActivity>(bundle, flags, right_in, left_out)
    }
}
fun navigationToEvent(context: Context){
    if (context != null && context is Activity) {
        val activity = context
        val flags = context.flags(Intent.FLAG_ACTIVITY_NEW_TASK, Intent.FLAG_ACTIVITY_CLEAR_TOP)
        activity.start<EventActivity>(flags, right_in, left_out)
    }
}
fun navigateToReport(context: Context){
    if (context != null && context is Activity) {
        val activity = context
        val flags = context.flags(Intent.FLAG_ACTIVITY_NEW_TASK, Intent.FLAG_ACTIVITY_CLEAR_TOP)
        activity.start<ReportActivity>(flags, right_in, left_out)
    }
}

fun navigateToAbsent(context: Context){
    if (context != null && context is Activity) {
        val activity = context
        val flags = context.flags(Intent.FLAG_ACTIVITY_NEW_TASK, Intent.FLAG_ACTIVITY_CLEAR_TOP)
        activity.start<AbsentActivity>(flags, right_in, left_out)
    }
}
fun backToLogin(context: Context) {
    if (context != null && context is Activity) {
        val activity = context
        val flags = context.flags(Intent.FLAG_ACTIVITY_NEW_TASK, Intent.FLAG_ACTIVITY_CLEAR_TOP)
        activity.start<LoginActivity>(flags, left_in, right_out)
    }
}

fun backToDashboard(context: Context){
    if (context != null && context is Activity) {
        val activity = context
        val flags = context.flags(Intent.FLAG_ACTIVITY_NEW_TASK, Intent.FLAG_ACTIVITY_CLEAR_TOP)
        activity.start<MainActivity>(flags, left_in, right_out)
    }
}
fun backToInformation(context: Context){
    if (context != null && context is Activity) {
        val activity = context
        val flags = context.flags(Intent.FLAG_ACTIVITY_NEW_TASK, Intent.FLAG_ACTIVITY_CLEAR_TOP)
        activity.start<InformationActivity>(flags, left_in, right_out)
    }
}
