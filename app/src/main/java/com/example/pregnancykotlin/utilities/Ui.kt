package com.example.pregnancykotlin.utilities

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.text.style.StyleSpan
import android.text.style.TextAppearanceSpan
import android.widget.TextView


object Ui {
    fun highlightTextPart(
        str: String,
        index: Int,
        regularExpression: String
    ): Spannable {
        var startPos = 0
        var endPos = str.length
        val textParts =
            str.split(regularExpression.toRegex()).toTypedArray()
//        if (index < 0 || index > textParts.size - 1) {
//            return
//        }
        if (textParts.size > 1) {
            startPos = str.indexOf(textParts[index])
            endPos = str.indexOf(regularExpression, startPos)
            if (endPos == -1) {
                endPos = str.length
            }
        }
        val spannable: Spannable = SpannableString(str)
        val blueColor =
            ColorStateList(arrayOf(intArrayOf()), intArrayOf(Color.RED))
        val textAppearanceSpan =
            TextAppearanceSpan(null, Typeface.BOLD, -1, blueColor, null)
        val backgroundColorSpan =
            BackgroundColorSpan(Color.parseColor("#fff000"))
        spannable.setSpan(textAppearanceSpan, startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//        spannable.setSpan(backgroundColorSpan, startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannable
    }
    fun setBoldSpannable(myText: String ,startIndex: Int,endIndex :Int): SpannableString {
        val spannableContent = SpannableString(myText)
        val blackColor =
            ColorStateList(arrayOf(intArrayOf()), intArrayOf(Color.BLACK))
        val textAppearanceSpan =
            TextAppearanceSpan(null, Typeface.BOLD, -1,blackColor , null)
        spannableContent.setSpan(textAppearanceSpan, startIndex,  endIndex, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        return spannableContent
    }

}