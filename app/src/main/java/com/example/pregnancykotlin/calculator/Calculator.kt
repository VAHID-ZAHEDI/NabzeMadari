package com.example.pregnancykotlin.calculator

import android.util.Log
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import kotlin.math.pow

class Calculator {
    fun calculateBmi(weight: Float, height: Int): Float {
        var height = height.toFloat() / 100
        return weight / height.pow(2)
    }

    fun calculateChildBirth(dayOfMonth: Int, monthOfYear: Int, year: Int): String {
        var eDay = dayOfMonth + 7
        var eMonth = monthOfYear - 3
        var eYear = year
        if (monthOfYear <= 3) {
            when (eMonth) {
                0 -> eMonth = 12
                -1 -> eMonth = 11
                -2 -> eMonth = 10
            }
        } else {
            eYear++
        }
        if (monthOfYear > 6) {
            if (eDay > 30) {
                eDay -= 30
                eMonth++
            }
        } else if (monthOfYear <= 6) {
            if (eDay > 31) {
                eDay -= 31
                eMonth++
            }

        }
        var persianCalendar = PersianCalendar()
        persianCalendar.setPersianDate(eYear, eMonth, eDay)

        return persianCalendar.persianLongDate
    }

    fun getWeeksDifference(fromDate: PersianCalendar, toDate: PersianCalendar): String {

        fromDate?.setPersianDate(fromDate.persianYear, fromDate.persianMonth, fromDate.persianDay)
        toDate?.setPersianDate(toDate.persianYear, toDate.persianMonth, toDate.persianDay)
        var differenceMiliSecond = toDate.timeInMillis - fromDate.timeInMillis
        var weekPast = (differenceMiliSecond) / (1000 * 60 * 60 * 24 * 7)
        var dayPast = differenceMiliSecond % (1000 * 60 * 60 * 24 * 7)/(1000*60*60*24)
        var result=" سن بارداری شما برابر است با $weekPast هفته و $dayPast روز "
        Log.d("vvv", "getDaysDifference: $result")
        return if (fromDate == null || toDate == null) "0" else (result)
    }
}