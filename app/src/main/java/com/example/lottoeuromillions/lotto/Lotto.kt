package com.example.lottoeuromillions.lotto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lotto")
data class Lotto(
    val number: Int,
    val count: Int
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}