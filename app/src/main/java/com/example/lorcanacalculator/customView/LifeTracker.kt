package com.example.lorcanacalculator.customView

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lorcanacalculator.Card

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun designLifeTracker(modifier: Modifier = Modifier, addCard: (Card) -> Unit) {
    var points by remember {
        mutableStateOf(0)
    }
    var i by remember {
        mutableStateOf(0)
    }
    Box(
        modifier = modifier
            .width(200.dp)
            .fillMaxHeight()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.1f)
                .background(Color.White)
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Plus",
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .combinedClickable(
                        onClick = { points++ },
                        onLongClick = {
                            addCard(Card(i))
                            i++
                        }),
                tint = Color.White
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Minus",
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .clickable {
                        if (points > 0) points--
                    },
                tint = Color.White
            )
        }
        Text(
            text = points.toString(),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            textAlign = TextAlign.Center,
            fontSize = 48.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}