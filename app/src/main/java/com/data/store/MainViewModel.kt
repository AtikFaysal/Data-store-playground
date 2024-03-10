package com.data.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.store.datastore.PrefDataStoreConstants
import com.data.store.datastore.PreferencesDataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository : PreferencesDataStoreRepository,
) : ViewModel(){

    fun getPreferenceData(){
        viewModelScope.launch {
            val data = repository.getFirstData(PrefDataStoreConstants.IS_REMEMBERED)
        }
    }
}