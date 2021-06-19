package io.github.kdk96.tanto.sample.data

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Storage @Inject constructor(
    private val preferences: SharedPreferences
) {

    init {
        Log.d("sample", "preferences hashCode: ${preferences.hashCode()}")
        Log.d("sample", "storage hashCode: ${hashCode()} init")
    }

    fun clear() {
        Log.d("sample", "storage hashCode: ${hashCode()} clear")
        preferences.edit { clear() }
    }
}
