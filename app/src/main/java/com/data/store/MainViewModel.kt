package com.data.store

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.store.datastore.PrefDataStoreConstants
import com.data.store.datastore.PreferencesDataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository : PreferencesDataStoreRepository,
) : ViewModel(){

    var formData by mutableStateOf(MainState())

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val uiAction : (UiAction) -> Unit = {
        when(it){
            is UiAction.OnChangePassword -> formData = formData.copy(password = it.password)
            is UiAction.OnChangeUserName -> formData = formData.copy(userName = it.userName)
            is UiAction.OnChangeRemember -> {
                formData = formData.copy(isRemembered = it.isRemember)
                savePreferenceData()
            }
        }
    }

    private var _uiState = MutableStateFlow<UiState>(UiState.Default)
    val uiState = _uiState.asStateFlow()

    init {
        getPreferenceData()
    }

    private fun getPreferenceData(){
        viewModelScope.launch {
            formData = formData.copy(
                userName = repository.getFirstData(PrefDataStoreConstants.USER_NAME) ?: "",
                password = repository.getFirstData(PrefDataStoreConstants.PASSWORD) ?: "",
                isRemembered = repository.getFirstData(PrefDataStoreConstants.IS_REMEMBERED)
                    ?: false
            )
        }
    }

    private fun savePreferenceData(){
        viewModelScope.launch {
            if(formData.isRemembered){
                repository.putData(PrefDataStoreConstants.IS_REMEMBERED, formData.isRemembered)
                repository.putData(PrefDataStoreConstants.USER_NAME, formData.userName)
                repository.putData(PrefDataStoreConstants.PASSWORD, formData.password)
                _uiEvent.send(UiEvent.ShowToastMessage("Data remembered"))
            }else {
                repository.clearData()
                _uiEvent.send(UiEvent.ShowToastMessage("Data cleared"))
            }
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
}