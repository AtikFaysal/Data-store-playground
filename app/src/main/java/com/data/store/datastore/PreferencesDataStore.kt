package com.data.store.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesDataStore @Inject constructor(
    private val dataStore : DataStore<Preferences>
) {
    suspend fun <T> getPrefData(key : Preferences.Key<T>) : Flow<T?>{
        return dataStore.data.map {
            it[key]
        }
    }

    suspend fun <T> getSinglePrefData(key : Preferences.Key<T>) : T?{
        return dataStore.data.first()[key]
    }

    suspend fun <T> putPrefData(key : Preferences.Key<T>, value : T){
        dataStore.edit {
            it[key] = value
        }
    }
}