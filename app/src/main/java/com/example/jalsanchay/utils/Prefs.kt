package com.example.jalsanchay.utils

import android.content.Context

object Prefs {

    private const val PREFS_NAME = "jalsanchay_prefs"
    private const val KEY_ROOF_AREA = "roof_area"
    private const val KEY_TANK_CAPACITY = "tank_capacity"
    private const val KEY_DAILY_USAGE = "daily_usage"
    private const val KEY_RUNOFF_COEFF = "runoff_coeff"
    private const val KEY_SETUP_DONE = "setup_done"

    fun saveSetup(
        context: Context,
        roofArea: Double,
        tankCapacity: Double,
        dailyUsage: Double = 540.0,
        runoffCoeff: Double = 0.85
    ) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putFloat(KEY_ROOF_AREA, roofArea.toFloat())
            .putFloat(KEY_TANK_CAPACITY, tankCapacity.toFloat())
            .putFloat(KEY_DAILY_USAGE, dailyUsage.toFloat())
            .putFloat(KEY_RUNOFF_COEFF, runoffCoeff.toFloat())
            .putBoolean(KEY_SETUP_DONE, true)
            .apply()
    }

    fun getRoofArea(context: Context): Double {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getFloat(KEY_ROOF_AREA, 0f).toDouble()
    }

    fun getTankCapacity(context: Context): Double {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getFloat(KEY_TANK_CAPACITY, 0f).toDouble()
    }

    fun getDailyUsage(context: Context): Double {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getFloat(KEY_DAILY_USAGE, 540f).toDouble()
    }

    fun getRunoffCoeff(context: Context): Double {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getFloat(KEY_RUNOFF_COEFF, 0.85f).toDouble()
    }

    fun isSetupDone(context: Context): Boolean {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getBoolean(KEY_SETUP_DONE, false)
    }

    fun clearAll(context: Context) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit().clear().apply()
    }
}