package com.example.pregnancykotlin.calculator

class CalculatorRepository : CalculatorDataSource {
    val calculator = Calculator()
    override fun calculateBmi(weight: Float, height: Int): Float {
        return calculator.calculateBmi(weight, height)
    }
}