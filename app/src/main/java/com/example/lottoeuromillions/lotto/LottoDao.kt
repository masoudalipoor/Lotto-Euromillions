package com.example.lottoeuromillions.lotto

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LottoDao {

    @Query("SELECT number, SUM(count) as count FROM lotto GROUP BY number ORDER BY count DESC")
    fun getAllLotto(): LiveData<List<Lotto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToLotto(lotto: Lotto)

    @Query("INSERT INTO lotto (number, count) VALUES(:number, -1)")
    suspend fun subFromLotto(number: Int)

    @Query("SELECT SUM(count) FROM lotto WHERE number = :number")
    suspend fun getCount(number: Int): Int

    @Query("DELETE FROM lotto WHERE number=:number")
    suspend fun removeFromLotto(number: Int)
}