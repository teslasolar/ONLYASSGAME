package io.onlyass.game

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RevealScreen(state: GameState, onNext: () -> Unit) {
    val sc = state.scenario
    val choice = sc.choices[state.selectedChoice]
    val broke = choice.isBreak
    val pipeColor = Color(state.pipe.color)

    Column(
        Modifier.fillMaxSize().background(Color(0xFF040408))
            .verticalScroll(rememberScrollState()).padding(16.dp)
    ) {
        Text(
            if (broke) "RULE BROKEN ✓" else "RULE FOLLOWED ✗",
            color = if (broke) Color(0xFF4a9a44) else Color(0xFF9b3da0),
            fontSize = 20.sp, fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
        )
        Spacer(Modifier.height(4.dp))
        Text(
            if (broke) "Score × ${state.pipe.prime}" else "Score unchanged",
            color = Color(0xFF667), fontSize = 10.sp, fontFamily = FontFamily.Monospace,
        )

        Spacer(Modifier.height(16.dp))

        // Show both choices with explanations
        sc.choices.forEachIndexed { idx, c ->
            val isSel = idx == state.selectedChoice
            val bg = when {
                c.isBreak -> Color(0xFF0a1a0a)
                else -> Color(0xFF1a0a0a)
            }
            Box(Modifier.fillMaxWidth().padding(vertical = 4.dp)
                .clip(RoundedCornerShape(8.dp)).background(bg)
                .padding(12.dp)) {
                Column {
                    Row {
                        Text(if (c.isBreak) "🔓" else "📏", fontSize = 14.sp)
                        Spacer(Modifier.width(6.dp))
                        Text(c.text, color = Color(0xFFaac), fontSize = 11.sp,
                            lineHeight = 16.sp)
                    }
                    Spacer(Modifier.height(6.dp))
                    Text(c.explain,
                        color = if (c.isBreak) Color(0xFF4a9a44) else Color(0xFF9b3da0),
                        fontSize = 10.sp, lineHeight = 14.sp)
                    if (isSel) {
                        Text("← your choice", color = Color(0xFF556),
                            fontSize = 8.sp, fontFamily = FontFamily.Monospace)
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))
        Text(sc.lesson, color = pipeColor, fontSize = 11.sp,
            fontFamily = FontFamily.Monospace, lineHeight = 16.sp)

        Spacer(Modifier.height(24.dp))
        Text("Score: ${state.score}", color = Color(0xFF8cc88a), fontSize = 12.sp,
            fontFamily = FontFamily.Monospace)
        if (state.score == 510510L) {
            Text("S₅ = 0 · THE A.S.S. IS COHERENT", color = Color(0xFFd4a94a),
                fontSize = 12.sp, fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace)
        }

        Spacer(Modifier.height(24.dp))
        Box(Modifier.clip(RoundedCornerShape(8.dp))
            .background(pipeColor).clickable { onNext() }
            .padding(horizontal = 24.dp, vertical = 10.dp)
            .align(Alignment.CenterHorizontally)) {
            Text("NEXT →", color = Color.White, fontSize = 11.sp,
                fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
        }
    }
}
