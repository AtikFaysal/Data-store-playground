package com.data.store.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey

object PrefDataStoreConstants {
    val IS_REMEMBERED = booleanPreferencesKey("is_remember")
    val USER_NAME = stringPreferencesKey("username")
    val PASSWORD = stringPreferencesKey("password")
}