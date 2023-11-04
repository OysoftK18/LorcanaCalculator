package com.example.lorcanacalculator.customView

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lorcanacalculator.Card

@Composable
fun CardTracker(modifier: Modifier = Modifier, playerField: MutableList<Card> = mutableListOf<Card>()) {
    LazyRow(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (playerField.isNotEmpty()) {
            items(playerField) {
                CardDesign(card = it) {
                    playerField.remove(it)
                }
            }
        }
    }
}

@Composable
fun CardDesign(card: Card, onLongClick: (Card) -> Unit) {
    Box(modifier = Modifier
        .size(120.dp)
        .clip(shape = RoundedCornerShape(16.dp))
        .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(16.dp))
        .padding(16.dp)
    ) {
        Text(text = card.toString(), modifier = Modifier.clickable {
            onLongClick(card)
        })
        Column {
            Icon(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Increase damage",
                tint = Color.White
            )
            Icon(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Decrease damage",
                tint = Color.White
            )
        }

    }
}