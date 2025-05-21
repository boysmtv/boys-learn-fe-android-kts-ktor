package com.kotlin.learn.core.common.data.preferences


import android.content.SharedPreferences
import javax.inject.Inject
import androidx.core.content.edit

class PlainPrefManager @Inject constructor(private val sharedPref: SharedPreferences) {

    fun save(keyName: String, text: String) {
        sharedPref.edit {
            putString(keyName, text)
        }
    }

    fun save(keyName: String, value: Int) {
        sharedPref.edit {
            putInt(keyName, value)
        }
    }

    fun save(keyName: String, value: Float) {
        sharedPref.edit {
            putFloat(keyName, value)
        }
    }

    fun save(keyName: String, status: Boolean) {
        sharedPref.edit {
            putBoolean(keyName, status)
        }
    }

    fun getInt(keyName: String): Int {
        return sharedPref.getInt(keyName, 0)
    }

    fun getBoolean(keyName: String, defaultValue: Boolean): Boolean {
        return sharedPref.getBoolean(keyName, defaultValue)
    }

    fun getString(keyName: String): String? {
        return sharedPref.getString(keyName, "")
    }

    fun getFloat(keyName: String): Float {
        return sharedPref.getFloat(keyName, 0.0f)
    }

    fun clearSharedPreference() {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }

    fun removeValue(keyName: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.remove(keyName)
        editor.apply()
    }
}