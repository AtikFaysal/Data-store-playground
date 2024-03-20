package com.data.store.core

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.data.store.datastore.PreferencesDataStoreImpl
import com.data.store.datastore.PreferencesDataStoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindPrefRepository(roomRepoImpl: PreferencesDataStoreImpl): PreferencesDataStoreRepository
}