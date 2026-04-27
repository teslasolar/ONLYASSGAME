package io.onlyass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import io.onlyass.game.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var state by remember { mutableStateOf(GameState()) }
            when (state.phase) {
                Phase.MENU -> MenuScreen { state = state.copy(phase = Phase.PLAY) }
                Phase.PLAY -> PlayScreen(state) { state = state.choose(it) }
                Phase.REVEAL -> RevealScreen(state) { state = state.next() }
                Phase.RESULTS -> ResultsScreen(state) { state = GameState() }
            }
        }
    }
}
