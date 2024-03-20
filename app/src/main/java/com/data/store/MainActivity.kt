package com.data.store

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.data.store.ui.ThemePreviews
import com.data.store.ui.component.AppTextField
import com.data.store.ui.component.RoundPrimaryButton
import com.data.store.ui.theme.DataStorePlaygroundTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataStorePlaygroundTheme {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                LaunchedEffect(viewModel) {
                    viewModel.uiEvent.collect{
                        when(it){
                            is UiEvent.ShowToastMessage -> Toast.makeText(this@MainActivity, it.message,Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                when(uiState){
                    UiState.Default -> {
                        LoginUi(
                            userName = { viewModel.formData.userName },
                            password = { viewModel.formData.password },
                            isRemember = { viewModel.formData.isRemembered },
                            onChangePassword = {
                                viewModel.uiAction(UiAction.OnChangePassword(it))
                            },
                            onChangeUserName = {
                                viewModel.uiAction(UiAction.OnChangeUserName(it))
                            },
                            onChangeRemember = {
                                viewModel.uiAction(UiAction.OnChangeRemember(it))
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LoginUi(
    modifier: Modifier = Modifier,
    onChangeUserName: (String) -> Unit,
    onChangePassword: (String) -> Unit,
    onChangeRemember: (Boolean) -> Unit,
    userName: () -> String,
    password: () -> String,
    isRemember: () -> Boolean,
) {
    Surface(
        modifier = modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier.padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppTextField(
                modifier = Modifier,
                hint = "Enter username",
                leadIcon = Icons.Outlined.Email,
                onValueChanged = onChangeUserName,
                text = userName(),
                keyBoardType = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(25.dp))

            AppTextField(
                modifier = Modifier,
                hint = "Enter password",
                leadIcon = Icons.Outlined.Lock,
                onValueChanged = onChangePassword,
                text = password(),
                keyBoardType = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
            )

            Spacer(modifier = Modifier.height(25.dp))

            RoundPrimaryButton(
                modifier = modifier.fillMaxWidth(),
                buttonText = "Login",
            ) {

            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isRemember(),
                    onCheckedChange = onChangeRemember,
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.primary,
                        uncheckedColor = Color.Gray,
                        checkmarkColor = Color.Gray,
                    )
                )
                Text(text = "Remember me")
            }
        }
    }
}

@ThemePreviews
@Composable
fun GreetingPreview() {
    //LoginUi()
}