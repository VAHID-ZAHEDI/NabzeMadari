package com.example.pregnancykotlin.calculator

interface CalculatorDataSource {
    fun calculateBmi(weight: Float, height: Int): Float
}