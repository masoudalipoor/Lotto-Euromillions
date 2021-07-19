package com.example.lottoeuromillions.euro.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lottoeuromillions.AppDatabase
import com.example.lottoeuromillions.euro.Euro
import com.example.lottoeuromillions.euro.Lucky
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class EuroViewModel : ViewModel() {
    private val db =
        AppDatabase.getInstance()
    private val euroDao = db.getEuroDao()
    private val luckyDao = db.getLuckyDao()

    fun saveEuro(number: Int) {
        viewModelScope.launch(IO) {
            val euro = Euro(number, 1)
            euroDao.addToEuro(euro)
        }
    }

    fun saveLucky(number: Int) {
        viewModelScope.launch(IO) {
            val lucky = Lucky(number, 1)
            luckyDao.addToLucky(lucky)
        }
    }
}