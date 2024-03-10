package com.data.store.datastore

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface PreferencesDataStoreRepository {
    suspend fun<T> getData(key: Preferences.Key<T>): Flow<T?>

    suspend fun<T> getFirstData(key: Preferences.Key<T>): T?

    suspend fun<T> putData(key:Preferences.Key<T>,value:T)
}