package com.biBalance.myapplication.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BiCheckbox(
    checkedState: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    shape: Shape = CircleShape
) {
    Box(
        modifier = modifier
            .size(size)
            .graphicsLayer {
                alpha = if (checkedState) 1f else 0.6f
            }
            .clickable { onCheckedChange(!checkedState) }
            .clip(shape),
    ) {
        Checkbox(
            checked = checkedState,
            onCheckedChange = null,
            modifier = Modifier.size(size),
        )
    }
}

@Composable
fun BiCheckbox() {
    val isChecked = remember { mutableStateOf(false) }
    val checkedText = remember { mutableStateOf("unChecked") }
    val circleSize = remember { mutableStateOf(20.dp) }
    val circleThickness = remember { mutableStateOf(2.dp) }
    val color = remember { mutableStateOf(Color.Gray) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .toggleable(value = isChecked.value, role = Role.Checkbox) {
                isChecked.value = it

                if (isChecked.value) {
                    checkedText.value = "Checked"
                    circleSize.value = 40.dp
                    circleThickness.value = 3.dp
                    color.value = Color.Black
                } else {
                    checkedText.value = "unChecked"
                    circleSize.value = 20.dp
                    circleThickness.value = 2.dp
                    color.value = Color.Gray
                }
            }) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(circleSize.value)
                .background(color.value)
                .padding(circleThickness.value)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            if (isChecked.value) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "")
            }
        }

        Text(
            text = checkedText.value,
            color = color.value,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 5.dp)
        )
    }
}