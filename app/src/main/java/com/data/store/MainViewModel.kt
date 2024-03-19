package com.data.store

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.store.datastore.PrefDataStoreConstants
import com.data.store.datastore.PreferencesDataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository : PreferencesDataStoreRepository,
) : ViewModel(){

    val formData by mutableStateOf(MainState())

    val uiAction : (UiAction) -> Unit = {
        when(it){
            is UiAction.OnChangePassword -> formData.copy(password = it.password)
            is UiAction.OnChangeUserName -> formData.copy(userName = it.userName)
            is UiAction.RememberLoginData -> savePreferenceData()
            is UiAction.OnChangeRemember -> formData.copy(isRemembered = it.isRemember)
        }
    }

    private var _uiState = MutableStateFlow<UiState>(UiState.Default)
    val uiState = _uiState.asStateFlow()

    init {
        getPreferenceData()
    }

    private fun getPreferenceData(){
        viewModelScope.launch {
            formData.copy(
                userName = repository.getFirstData(PrefDataStoreConstants.USER_NAME) ?: "",
                password = repository.getFirstData(PrefDataStoreConstants.PASSWORD) ?: "",
                isRemembered = repository.getFirstData(PrefDataStoreConstants.IS_REMEMBERED)
                    ?: false
            )
        }
    }

    private fun savePreferenceData(){
        viewModelScope.launch {
            repository.putData(PrefDataStoreConstants.IS_REMEMBERED, formData.isRemembered)
            repository.putData(PrefDataStoreConstants.USER_NAME, formData.userName)
            repository.putData(PrefDataStoreConstants.PASSWORD, formData.password)
        }
    }
}

data class MainState(
    val userName : String = "",
    val password : String = "",
    val isRemembered : Boolean = false,
    val isUserNameValid : Boolean = true,
    val isPasswordValid : Boolean = true
)

sealed interface UiState{
    data object Default : UiState
}

sealed interface UiEvent{
    data class ShowToastMessage(val message : String) : UiEvent
}

sealed interface UiAction {
    data class OnChangeUserName(val userName : String) : UiAction
    data class OnChangePassword(val password : String) : UiAction
    data class OnChangeRemember(val isRemember : Boolean) : UiAction
    data object RememberLoginData : UiAction
}