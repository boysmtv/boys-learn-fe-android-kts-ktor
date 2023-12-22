package com.kotlin.learn.core.common.data.preferences


import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPrefManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val secretPreferences = "encrypted-based-preference"
    private val masterKeys = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    fun getSharedPref(): SharedPreferences {
        return EncryptedSharedPreferences.create(
            secretPreferences,
            masterKeys,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

}