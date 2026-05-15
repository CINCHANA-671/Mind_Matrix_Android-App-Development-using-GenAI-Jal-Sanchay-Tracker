package com.example.jalsanchay.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rainfall_entries")
data class RainfallEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val rainfallMm: Double,
    val litersCollected: Double,
    val dateMillis: Long = System.currentTimeMillis()
)