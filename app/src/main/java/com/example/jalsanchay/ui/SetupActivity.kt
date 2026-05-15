package com.example.jalsanchay.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jalsanchay.R
import com.example.jalsanchay.utils.Prefs
import com.google.android.material.textfield.TextInputEditText

class SetupActivity : AppCompatActivity() {

    private lateinit var etRoofArea: TextInputEditText
    private lateinit var etTankCapacity: TextInputEditText
    private lateinit var etDailyUsage: TextInputEditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // If setup already done, go straight to MainActivity
        if (Prefs.isSetupDone(this)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_setup)

        etRoofArea = findViewById(R.id.etRoofArea)
        etTankCapacity = findViewById(R.id.etTankCapacity)
        etDailyUsage = findViewById(R.id.etDailyUsage)
        btnSave = findViewById(R.id.btnSaveSetup)

        btnSave.setOnClickListener {
            saveSetup()
        }
    }

    private fun saveSetup() {
        val roofStr = etRoofArea.text.toString().trim()
        val tankStr = etTankCapacity.text.toString().trim()
        val usageStr = etDailyUsage.text.toString().trim()

        // Validate roof area
        val roofArea = roofStr.toDoubleOrNull()
        if (roofArea == null || roofArea <= 0) {
            etRoofArea.error = "Enter a valid roof area"
            return
        }

        // Validate tank capacity
        val tankCapacity = tankStr.toDoubleOrNull()
        if (tankCapacity == null || tankCapacity <= 0) {
            etTankCapacity.error = "Enter a valid tank capacity"
            return
        }

        // Daily usage is optional - default 540L
        val dailyUsage = usageStr.toDoubleOrNull() ?: 540.0

        // Save everything
        Prefs.saveSetup(
            context = this,
            roofArea = roofArea,
            tankCapacity = tankCapacity,
            dailyUsage = dailyUsage
        )

        Toast.makeText(this, "✅ Setup saved!", Toast.LENGTH_SHORT).show()

        // Go to main screen
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}