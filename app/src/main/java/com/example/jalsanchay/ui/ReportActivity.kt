package com.example.jalsanchay.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.jalsanchay.R
import com.example.jalsanchay.data.RainfallRepository
import com.example.jalsanchay.utils.Prefs
import com.example.jalsanchay.utils.WaterCalculator
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ReportActivity : AppCompatActivity() {

    private lateinit var tvReport: TextView
    private lateinit var repository: RainfallRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        tvReport = findViewById(R.id.tvReport)
        repository = RainfallRepository(applicationContext)

        loadMonthlyReport()
    }

    private fun loadMonthlyReport() {
        // Get start of current month
        val calendar = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_MONTH, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        val startOfMonth = calendar.timeInMillis
        val monthName = SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(Date())
        val dailyUsage = Prefs.getDailyUsage(this)

        repository.getMonthlyLiters(startOfMonth).observe(this) { liters ->
            val l = liters ?: 0.0
            val days = WaterCalculator.toHouseholdDays(l, dailyUsage)
            val co2Saved = l * 0.0003

            tvReport.text = """
                📊 MONTHLY REPORT
                ══════════════════════════
                
                📅 Month: $monthName
                
                💧 Total Water Collected:
                   ${"%.1f".format(l)} Liters
                
                🏠 Household Days Covered:
                   ${"%.1f".format(days)} days
                
                🌱 Estimated CO₂ Saved:
                   ${"%.3f".format(co2Saved)} kg
                
                ══════════════════════════
                🌍 You are helping India
                meet its water conservation
                targets — one drop at a time!
            """.trimIndent()
        }
    }
}