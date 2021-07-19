package com.example.lottoeuromillions.lotto.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lottoeuromillions.AppDatabase
import com.example.lottoeuromillions.lotto.Lotto
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class LottoResultViewModel : ViewModel() {
    private val db =
        AppDatabase.getInstance()
    private val lottoDao = db.getLottoDao()

    fun sub(number: Int) {
        viewModelScope.launch(IO) {
            val count = lottoDao.getCount(number)
            when {
                count == 1 -> lottoDao.removeFromLotto(number)
                count > 1 -> lottoDao.subFromLotto(number)
            }
        }
    }

    fun getAllLotto(): LiveData<List<Lotto>> {
        return lottoDao.getAllLotto()
    }
}
