package com.example.jalsanchay.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RainfallDao {

    @Insert
    suspend fun insert(entry: RainfallEntry)

    @Query("SELECT * FROM rainfall_entries ORDER BY dateMillis DESC")
    fun getAllEntries(): LiveData<List<RainfallEntry>>

    @Query("SELECT SUM(litersCollected) FROM rainfall_entries")
    fun getTotalLiters(): LiveData<Double?>

    @Query("""
        SELECT SUM(litersCollected) FROM rainfall_entries 
        WHERE dateMillis >= :startOfMonth
    """)
    fun getMonthlyLiters(startOfMonth: Long): LiveData<Double?>
}