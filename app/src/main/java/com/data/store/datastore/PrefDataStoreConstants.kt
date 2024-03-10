package com.data.store.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey

object PrefDataStoreConstants {
    val IS_REMEMBERED = booleanPreferencesKey("is_remember")
    val USER_NAME = stringSetPreferencesKey("username")
    val PASSWORD = stringSetPreferencesKey("password")
}