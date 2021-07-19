package com.example.lottoeuromillions.euro

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lottoeuromillions.euro.Lucky

@Dao
interface LuckyDao {

    @Query("SELECT number, SUM(count) as count FROM lucky GROUP BY number ORDER BY count DESC")
    fun getAllLucky(): LiveData<List<Lucky>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToLucky(lucky: Lucky)

    @Query("INSERT INTO lucky (number, count) VALUES(:number, -1)")
    suspend fun subFromLucky(number: Int)

    @Query("SELECT SUM(count) FROM lucky WHERE number = :number")
    suspend fun getCount(number: Int): Int

    @Query("DELETE FROM lucky WHERE number=:number")
    suspend fun removeFromLucky(number: Int)
}