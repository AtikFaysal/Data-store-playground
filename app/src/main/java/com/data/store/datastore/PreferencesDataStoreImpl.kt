package com.data.store.datastore

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PreferencesDataStoreImpl @Inject constructor(
    private val store: PreferencesDataStore
) : PreferencesDataStoreRepository{

    override suspend fun <T> getData(key: Preferences.Key<T>): Flow<T?> =
        store.getPrefData(key)

    override suspend fun <T> getFirstData(key: Preferences.Key<T>): T? =
        store.getSinglePrefData(key)

    override suspend fun <T> putData(key: Preferences.Key<T>, value: T) =
        store.putPrefData(key, value)

    override suspend fun clearData() = store.clearAllData()
}