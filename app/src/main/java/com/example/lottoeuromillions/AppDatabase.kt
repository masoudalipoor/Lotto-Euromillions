package com.example.lottoeuromillions

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lottoeuromillions.euro.EuroDao
import com.example.lottoeuromillions.lotto.LottoDao
import com.example.lottoeuromillions.euro.Euro
import com.example.lottoeuromillions.euro.Lucky
import com.example.lottoeuromillions.lotto.Lotto
import com.example.lottoeuromillions.euro.LuckyDao

@Database(entities = [Lotto::class, Euro::class, Lucky::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object{
        private var instance: AppDatabase? = null

        fun getInstance(): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(MyApp.context, AppDatabase::class.java, "lotto_euro")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }

    abstract fun getLottoDao(): LottoDao
    abstract fun getEuroDao(): EuroDao
    abstract fun getLuckyDao(): LuckyDao
}