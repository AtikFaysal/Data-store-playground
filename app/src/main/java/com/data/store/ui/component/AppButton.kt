package com.data.store.ui.component

import androidx.annotation.DimenRes
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.data.store.ui.ThemePreviews
import com.data.store.ui.theme.Typography

@Composable
fun RoundPrimaryButton(
    modifier: Modifier = Modifier,
    buttonColor : Color = Color.Black,
    buttonText : String = "",
    onButtonClick : () -> Unit
) {
    Button(
        modifier = modifier.height(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
        shape = RoundedCornerShape(100),
        onClick = onButtonClick
    ) {
        Text(
            text = buttonText,
            textAlign = TextAlign.Center,
            color = Color.White,
            style = Typography.bodyLarge,
            modifier = Modifier.wrapContentSize()
        )
    }
}

@Composable
@ThemePreviews
fun PreviewButton() {
    RoundPrimaryButton(buttonText = "Login"){

    }
}
