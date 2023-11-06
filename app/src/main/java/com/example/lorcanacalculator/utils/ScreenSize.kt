package com.example.lorcanacalculator.utils

import android.content.res.Configuration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun screenWidth(configuration: Configuration): Dp {
    return configuration.screenWidthDp.dp

}
fun screenHeight(configuration: Configuration): Dp{
    return configuration.screenHeightDp.dp
}