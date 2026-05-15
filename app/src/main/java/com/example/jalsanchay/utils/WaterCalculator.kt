package com.example.jalsanchay.utils

object WaterCalculator {

    // Main Formula:
    // Area (sq.ft) × Rainfall (mm) × 0.0929 × Runoff Coefficient
    // 0.0929 converts square feet × mm into Liters
    fun calculateLiters(
        roofAreaSqFt: Double,
        rainfallMm: Double,
        runoffCoefficient: Double = 0.85
    ): Double {
        if (roofAreaSqFt <= 0 || rainfallMm <= 0) return 0.0
        return roofAreaSqFt * rainfallMm * 0.0929 * runoffCoefficient
    }

    // Converts total liters into household water days
    // Average family of 4 uses 135L per person = 540L per day
    fun toHouseholdDays(
        liters: Double,
        dailyUsageLiters: Double = 540.0
    ): Double {
        if (dailyUsageLiters <= 0) return 0.0
        return liters / dailyUsageLiters
    }

    // Validates rainfall input from user
    fun validateRainfall(input: String): Double? {
        val value = input.toDoubleOrNull() ?: return null
        if (value <= 0) return null
        return value
    }

    // Validates roof area input from user
    fun validateRoofArea(input: String): Double? {
        val value = input.toDoubleOrNull() ?: return null
        if (value <= 0) return null
        return value
    }
}