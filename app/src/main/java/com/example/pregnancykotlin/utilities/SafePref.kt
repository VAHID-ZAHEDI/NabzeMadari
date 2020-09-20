package com.example.pregnancykotlin.utilities

import android.content.Context
import android.content.Context.MODE_PRIVATE
import javax.inject.Inject


class SafePref @Inject constructor(
  val context: Context,
  val zcript: Zcript
) {
  private val sharedPreferences = context.getSharedPreferences("daggerPref", MODE_PRIVATE)
  val editor = sharedPreferences.edit()


  fun setSharedPreferences(name: String?, `object`: Any?): Boolean {
    if (`object` is String) {
      val value = zcript.encrypt(`object`)
      editor.putString(
        name,
        value as String?
      )
    } else if (`object` is Int) editor.putInt(
      name,
      (`object` as Int?)!!
    ) else if (`object` is Long) editor.putLong(
      name,
      (`object` as Long?)!!
    ) else if (`object` is Float) editor.putFloat(
      name,
      (`object` as Float?)!!
    ) else if (`object` is Boolean) editor.putBoolean(name, (`object` as Boolean?)!!)
    return editor.commit()
  }

  fun getSharedPreferences(name: String?, type: PrefObject): Any? {
    var o: Any? = null
    when (type) {
      PrefObject.STRING -> o = zcript.decrypt(sharedPreferences.getString(name, "") as String)
      PrefObject.INT -> o = sharedPreferences.getInt(name, 0)
      PrefObject.LONG -> o = sharedPreferences.getLong(name, 0)
      PrefObject.FLOAT -> o = sharedPreferences.getFloat(name, 0f)
      PrefObject.BOOLEAN -> o = sharedPreferences.getBoolean(name, false)
    }
    return o
  }
}