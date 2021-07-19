package com.example.lottoeuromillions.lotto.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lottoeuromillions.AppDatabase
import com.example.lottoeuromillions.lotto.Lotto
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class LottoViewModel : ViewModel() {
    private val db =
        AppDatabase.getInstance()
    private val lottoDao = db.getLottoDao()

    fun saveLotto(number: Int) {
        viewModelScope.launch(IO) {
            val lotto = Lotto(number, 1)
            lottoDao.addToLotto(lotto)
        }
    }
}