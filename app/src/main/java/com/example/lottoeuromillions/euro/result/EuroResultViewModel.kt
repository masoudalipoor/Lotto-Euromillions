    package com.example.lottoeuromillions.euro.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lottoeuromillions.AppDatabase
import com.example.lottoeuromillions.euro.Euro
import com.example.lottoeuromillions.euro.Lucky
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EuroResultViewModel : ViewModel() {
    private val db =
        AppDatabase.getInstance()
    private val euroDao = db.getEuroDao()
    private val luckyDao = db.getLuckyDao()

    fun getAllEuro(): LiveData<List<Euro>> {
        return euroDao.getAllEuro()
    }

    fun getAllLucky(): LiveData<List<Lucky>> {
        return luckyDao.getAllLucky()
    }

    fun subFromEuro(number: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val count = euroDao.getCount(number)
            when {
                count == 1 -> euroDao.removeFromEuro(number)
                count > 1 -> euroDao.subFromEuro(number)
            }
        }
    }

    fun subFromLucky(number: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val count = luckyDao.getCount(number)
            when {
                count == 1 -> luckyDao.removeFromLucky(number)
                count > 1 -> luckyDao.subFromLucky(number)
            }
        }
    }
}