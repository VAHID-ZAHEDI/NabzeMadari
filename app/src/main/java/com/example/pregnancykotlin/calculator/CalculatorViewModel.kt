package com.example.pregnancykotlin.calculator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class CalculatorViewModel @Inject constructor() : ViewModel() {
    var calculatorRepository = CalculatorRepository()
    fun calculateBmi(weight: Float, height: Int): MutableLiveData<Float> {
        var result: MutableLiveData<Float> = MutableLiveData()
        result.value=calculatorRepository.calculateBmi(weight, height)
        return result
    }
}