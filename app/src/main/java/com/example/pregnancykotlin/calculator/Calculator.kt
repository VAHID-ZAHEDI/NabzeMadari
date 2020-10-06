package com.example.pregnancykotlin.calculator

import android.util.Log
import kotlin.math.pow

class Calculator {
    fun calculateBmi(weight: Float, height: Int): Float {
        var height = height.toFloat() / 100
        return weight / height.pow(2)
    }
}