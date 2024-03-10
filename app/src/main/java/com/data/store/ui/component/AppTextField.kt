package com.data.store.ui.component

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    hint: String = "",
    text: String = "",
    onValueChanged: (String) -> Unit,
    leadIcon: ImageVector = Icons.Outlined.Email,
    trailIcon: ImageVector = Icons.Outlined.Clear,
    keyBoardType: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
    ),
) {
    OutlinedTextField(
        value = text,
        onValueChange = onValueChanged,
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White, shape = RoundedCornerShape(100)
            ),
        placeholder = { Text(hint) },
        shape = RoundedCornerShape(100),
        leadingIcon = {
            Icon(
                imageVector = leadIcon, contentDescription = "", tint = Color.Black
            )
        },
        trailingIcon = null,
        maxLines = 1,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            unfocusedBorderColor = Color.Gray,
            focusedBorderColor = Color.Black,
            unfocusedPlaceholderColor = Color.Gray,
            focusedPlaceholderColor = Color.Black,
        ),
        keyboardOptions = keyBoardType,
    )

}