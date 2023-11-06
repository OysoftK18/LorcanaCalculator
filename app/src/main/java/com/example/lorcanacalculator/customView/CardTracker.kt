package com.example.lorcanacalculator.customView

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lorcanacalculator.data.Card

@Composable
fun CardTracker(
    modifier: Modifier = Modifier,
    list: MutableList<Card> = mutableListOf()
) {
    val playerField = remember {
        mutableStateOf(list)
    }

    LazyRow(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (playerField.value.isNotEmpty()) {
            itemsIndexed(playerField.value) { ind, card ->
                CardDesign(
                    card = card,
                    onClick = { playerField.value[ind] = card.copy(damageCounter = it) },
                    onLongClick = { playerField.value.remove(it) })
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardDesign(
    card: Card,
    onClick: (Int) -> Unit,
    onLongClick: (Card) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .size(100.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .alpha(0.1f)
            .background(Color.White))
        Text(
            text = card.damageCounter.toString(),
            fontSize = 32.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Column {
            Icon(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .combinedClickable(
                        onClick = {
                            val newValue = card.damageCounter + 1
                            onClick(newValue)
                        },
                        onLongClick = { onLongClick(card) }),
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Increase damage",
                tint = Color.White
            )
            Icon(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .combinedClickable(
                        onClick = {
                            if (card.damageCounter != 0) {
                                val newValue = card.damageCounter - 1
                                onClick(newValue)
                            }
                        },
                        onLongClick = { onLongClick(card) }),
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Decrease damage",
                tint = Color.White
            )
        }

    }
}