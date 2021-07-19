package com.example.lottoeuromillions.euro

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lottoeuromillions.lotto.Lotto

@Dao
interface EuroDao {

    @Query("SELECT number, SUM(count) as count FROM euro GROUP BY number ORDER BY count DESC")
    fun getAllEuro(): LiveData<List<Euro>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToEuro(euro: Euro)

    @Query("INSERT INTO euro (number, count) VALUES(:number, -1)")
    suspend fun subFromEuro(number: Int)

    @Query("SELECT SUM(count) FROM euro WHERE number = :number")
    suspend fun getCount(number: Int): Int

    @Query("DELETE FROM euro WHERE number=:number")
    suspend fun removeFromEuro(number: Int)
}