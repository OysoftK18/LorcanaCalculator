package com.example.lorcanacalculator

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lorcanacalculator.ui.theme.LorcanaCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LorcanaCalculatorTheme {
                val context = LocalContext.current
                (context as? Activity)?.requestedOrientation =
                    ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    designHolder()
                }
            }
        }
    }
}

@Composable
fun designHolder() {

    var player2 by remember {
        mutableStateOf(mutableStateListOf<Card>())
    }

    Column {
        Row(
            modifier = Modifier
                .background(Color.Gray)
                .fillMaxWidth()
                .weight(1f)
        ) {
            designLifeTracker(Modifier.rotate(180.0f)) {
                player2.add(it)
            }
        }
        Divider(modifier = Modifier.fillMaxWidth(), thickness = 4.dp, color = Color.Black)
        Row(
            modifier = Modifier
                .background(Color.DarkGray)
                .fillMaxWidth()
                .weight(1f)
        ) {
            designLifeTracker() {
                player2.add(it)
            }

            LazyRow {
                if (player2.isNotEmpty()) {
                    items(player2) {
                        Text(text = it.toString(), modifier = Modifier.clickable {
                            player2.remove(it)
                        })
                    }
                }
            }
        }
    }
}

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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun designCardTracker(
    modifier: Modifier = Modifier,
    card: Card,
    destroyCard: (Card) -> Unit
) {
    var damage by remember {
        mutableStateOf(card.damageCounter)
    }
    Box(
        modifier = modifier
            .width(200.dp)
            .fillMaxHeight()
    ) {
        Icon(
            imageVector = Icons.Default.AccountBox,
            contentDescription = "Card",
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.1f),
            tint = Color.White
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Plus",
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .combinedClickable(onClick = {
                        damage++
                        card.damageCounter = damage
                    }, onLongClick = {
                        destroyCard(card)
                    }),

                tint = Color.White,

                )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Minus",
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .combinedClickable(
                        onClick = {
                            if (damage > 0) damage--
                            card.damageCounter = damage
                        },
                        onLongClick = {
                            Log.d("Test:", "Destroyed")
                        }
                    ),
                tint = Color.White
            )
        }
        Text(
            text = damage.toString(),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}