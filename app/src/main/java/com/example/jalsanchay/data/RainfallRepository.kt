package com.example.jalsanchay.data

import android.content.Context
import androidx.lifecycle.LiveData

class RainfallRepository(context: Context) {

    private val dao = AppDatabase.getInstance(context).rainfallDao()

    suspend fun insert(entry: RainfallEntry) {
        dao.insert(entry)
    }

    fun getAllEntries(): LiveData<List<RainfallEntry>> {
        return dao.getAllEntries()
    }

    fun getTotalLiters(): LiveData<Double?> {
        return dao.getTotalLiters()
    }

    fun getMonthlyLiters(startOfMonth: Long): LiveData<Double?> {
        return dao.getMonthlyLiters(startOfMonth)
    }
}