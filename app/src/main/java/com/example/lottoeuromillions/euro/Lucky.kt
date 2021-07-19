package com.example.lottoeuromillions.euro

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lucky")
data class Lucky(
    val number: Int,
    val count: Int
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}