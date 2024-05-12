package com.codeliner.simplecalc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private var counterValue = 0
    private val counterValueLiveData = MutableLiveData<Int>()

    fun getCounterValueLiveData(): LiveData<Int> = counterValueLiveData

    fun onButtonClick(buttonId: Int) {
        when (buttonId) {
            R.id.button_increase -> increaseCount()
            R.id.button_decrease -> decreaseCount()
            R.id.button_clear -> clearCount()
        }
    }
    private fun increaseCount() {

        ++counterValue
        if (counterValue > 10) counterValue = 10
        counterValueLiveData.value = counterValue
    }
    private fun decreaseCount() {

        --counterValue
        if (counterValue < 0) counterValue = 0
        counterValueLiveData.value = counterValue
    }
    private fun clearCount() {

        counterValue = 0
        counterValueLiveData.value = counterValue
    }
}

