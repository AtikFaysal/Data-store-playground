package com.data.store

import android.os.Bundle
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
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
                LoginUi()
            }
        }
    }
}

@Composable
fun LoginUi(
    modifier: Modifier = Modifier,
    userName : String = "",
    password : String = "",
    isRemembered : Boolean = false
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppTextField(
                modifier = modifier,
                hint = "Enter username",
                leadIcon = Icons.Outlined.Email,
                onValueChanged = {

                }
            )

            Spacer(modifier = Modifier.height(25.dp))

            AppTextField(
                modifier = modifier,
                hint = "Enter password",
                leadIcon = Icons.Outlined.Lock,
                onValueChanged = {

                }
            )

            Spacer(modifier = Modifier.height(25.dp))

            RoundPrimaryButton(
                modifier = modifier.fillMaxWidth(),
                buttonText = "Login",
            ) {

            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Checkbox(
                    checked = true,
                    onCheckedChange = {},
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
    LoginUi()
}