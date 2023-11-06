package com.example.lorcanacalculator

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.lorcanacalculator.customView.CardTracker
import com.example.lorcanacalculator.customView.DesignLifeTracker
import com.example.lorcanacalculator.data.Card
import com.example.lorcanacalculator.ui.theme.LorcanaCalculatorTheme
import com.example.lorcanacalculator.ui.theme.blueLorcana
import com.example.lorcanacalculator.ui.theme.purpleLorcana

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
                    DesignHolder()
                }
            }
        }
    }
}

@Composable
fun DesignHolder() {
    val player1 by remember {
        mutableStateOf(mutableStateListOf<Card>())
    }

    val player2 by remember {
        mutableStateOf(mutableStateListOf<Card>())
    }

    Column {
        Row(
            modifier = Modifier
                .background(purpleLorcana)
                .fillMaxWidth()
                .weight(1f)
        ) {
            DesignLifeTracker(Modifier.rotate(180.0f)) {
                player1.add(it)
            }

            CardTracker(Modifier.rotate(180.0f), player1)
        }
        Divider(modifier = Modifier.fillMaxWidth(), thickness = 4.dp, color = Color.Black)
        Row(
            modifier = Modifier
                .background(blueLorcana)
                .fillMaxWidth()
                .weight(1f)
        ) {
            DesignLifeTracker() {
                player2.add(it)
            }

            CardTracker(list = player2)

        }
    }
}