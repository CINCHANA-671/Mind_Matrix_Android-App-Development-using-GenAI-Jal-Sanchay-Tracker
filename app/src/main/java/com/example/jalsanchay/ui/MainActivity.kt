package com.example.jalsanchay.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.jalsanchay.R
import com.example.jalsanchay.data.RainfallEntry
import com.example.jalsanchay.data.RainfallRepository
import com.example.jalsanchay.utils.Prefs
import com.example.jalsanchay.utils.WaterCalculator
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var tvTotalLiters: TextView
    private lateinit var tvTodayLiters: TextView
    private lateinit var tvImpactScore: TextView
    private lateinit var tvTankPercent: TextView
    private lateinit var progressTank: ProgressBar
    private lateinit var etRainfall: TextInputEditText
    private lateinit var btnAddEntry: Button
    private lateinit var btnReport: Button
    private lateinit var btnTips: Button

    private lateinit var repository: RainfallRepository
    private var todayLiters = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTotalLiters = findViewById(R.id.tvTotalLiters)
        tvTodayLiters = findViewById(R.id.tvTodayLiters)
        tvImpactScore = findViewById(R.id.tvImpactScore)
        tvTankPercent = findViewById(R.id.tvTankPercent)
        progressTank = findViewById(R.id.progressTank)
        etRainfall = findViewById(R.id.etRainfall)
        btnAddEntry = findViewById(R.id.btnAddEntry)
        btnReport = findViewById(R.id.btnReport)
        btnTips = findViewById(R.id.btnTips)

        repository = RainfallRepository(applicationContext)

        val tankCapacity = Prefs.getTankCapacity(this)
        val dailyUsage = Prefs.getDailyUsage(this)

        repository.getTotalLiters().observe(this) { total ->
            val liters = total ?: 0.0
            tvTotalLiters.text = "%.1f L".format(liters)
            val percent = if (tankCapacity > 0)
                ((liters / tankCapacity) * 100).coerceIn(0.0, 100.0)
            else 0.0
            progressTank.progress = percent.toInt()
            tvTankPercent.text = "Tank: %.0f%% full".format(percent)
            val days = WaterCalculator.toHouseholdDays(liters, dailyUsage)
            tvImpactScore.text = "%.1f household water days saved".format(days)
        }

        btnAddEntry.setOnClickListener { addRainfallEntry() }
        btnReport.setOnClickListener {
            startActivity(Intent(this, ReportActivity::class.java))
        }
        btnTips.setOnClickListener {
            startActivity(Intent(this, TipsActivity::class.java))
        }
    }

    private fun addRainfallEntry() {
        val input = etRainfall.text.toString().trim()
        val rainfall = WaterCalculator.validateRainfall(input)

        if (rainfall == null) {
            etRainfall.error = "Enter a valid rainfall amount in mm"
            return
        }

        val roofArea = Prefs.getRoofArea(this)
        val runoffCoeff = Prefs.getRunoffCoeff(this)
        val liters = WaterCalculator.calculateLiters(roofArea, rainfall, runoffCoeff)

        todayLiters += liters
        tvTodayLiters.text = "Today: %.1f L".format(todayLiters)

        val entry = RainfallEntry(
            rainfallMm = rainfall,
            litersCollected = liters
        )

        lifecycleScope.launch {
            repository.insert(entry)
        }

        etRainfall.text?.clear()
        Toast.makeText(
            this,
            "💧 %.1f Liters collected!".format(liters),
            Toast.LENGTH_LONG
        ).show()
    }
}