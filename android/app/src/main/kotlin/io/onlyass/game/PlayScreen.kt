package io.onlyass.game

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
fun PlayScreen(state: GameState, onChoose: (Int) -> Unit) {
    val pipe = state.pipe
    val sc = state.scenario
    val pipeColor = Color(pipe.color)

    Column(
        Modifier.fillMaxSize().background(Color(0xFF040408))
            .verticalScroll(rememberScrollState()).padding(16.dp)
    ) {
        // Pipe header
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(28.dp).clip(RoundedCornerShape(14.dp))
                .background(pipeColor), contentAlignment = Alignment.Center) {
                Text(pipe.glyph, color = Color.White, fontSize = 14.sp)
            }
            Spacer(Modifier.width(8.dp))
            Column {
                Text("p=${pipe.prime} · ${pipe.name}", color = pipeColor,
                    fontSize = 12.sp, fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold)
                Text(pipe.desc, color = Color(0xFF667), fontSize = 9.sp,
                    fontFamily = FontFamily.Monospace)
            }
            Spacer(Modifier.weight(1f))
            Text("×${state.score}", color = Color(0xFF8cc88a), fontSize = 10.sp,
                fontFamily = FontFamily.Monospace)
        }

        Spacer(Modifier.height(20.dp))

        // Scenario
        Text(sc.setup, color = Color.White, fontSize = 14.sp, lineHeight = 20.sp)
        Spacer(Modifier.height(12.dp))
        Box(Modifier.fillMaxWidth().clip(RoundedCornerShape(6.dp))
            .background(Color(0xFF1a1e28)).padding(10.dp)) {
            Text("📏 ${sc.rule}", color = Color(0xFFffaa00), fontSize = 11.sp,
                lineHeight = 16.sp)
        }

        Spacer(Modifier.height(16.dp))
        Text("BREAK IT?", color = pipeColor, fontSize = 10.sp,
            fontFamily = FontFamily.Monospace, letterSpacing = 2.sp)
        Spacer(Modifier.height(8.dp))

        // Choices
        sc.choices.forEachIndexed { idx, choice ->
            val sel = state.selectedChoice == idx
            val border = if (sel) pipeColor else Color(0xFF1a1e28)
            Box(Modifier.fillMaxWidth().padding(vertical = 4.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(1.dp, border, RoundedCornerShape(8.dp))
                .background(if (sel) Color(0xFF0e1218) else Color(0xFF080c14))
                .clickable(enabled = !state.revealed) { onChoose(idx) }
                .padding(12.dp)) {
                Text(choice.text, color = Color(0xFFaac), fontSize = 12.sp,
                    lineHeight = 18.sp)
            }
        }

        if (state.streak > 1) {
            Spacer(Modifier.height(8.dp))
            Text("🔥 ${state.streak} streak", color = Color(0xFFffaa00),
                fontSize = 10.sp, fontFamily = FontFamily.Monospace)
        }
    }
}
