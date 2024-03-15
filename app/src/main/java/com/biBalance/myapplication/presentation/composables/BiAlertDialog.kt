package com.biBalance.myapplication.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BiAlertDialog(
    title: String = "Are you sure you want to logout?",
    confirmText: String = "Yes",
    onDismissButtonClick: () -> Unit,
    onConfirmButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.wrapContentSize(),
        onDismissRequest = { onDismissButtonClick() },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
            )
        },
        confirmButton = {
            OutlinedButton(
                onClick = { onConfirmButtonClick() },
                border = BorderStroke(1.dp,MaterialTheme.colorScheme.primary),
                modifier = Modifier.size(width = 102.dp, height = 33.dp),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = confirmText,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onDismissButtonClick() },
                colors = ButtonDefaults.textButtonColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier.size(width = 102.dp, height = 33.dp),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = "No",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center
                )

            }
        },
        containerColor = MaterialTheme.colorScheme.background
    )

}

@Preview(showBackground = true)
@Composable
fun XAlertDialogPreview() {
}