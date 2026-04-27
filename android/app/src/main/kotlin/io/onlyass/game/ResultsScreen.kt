package io.onlyass.game

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResultsScreen(state: GameState, onRestart: () -> Unit) {
    val coherent = state.coherent
    Column(
        Modifier.fillMaxSize().background(Color(0xFF040408)).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            if (coherent) "S₅ = 0" else "S₅ > 0",
            color = if (coherent) Color(0xFFd4a94a) else Color(0xFF9b3da0),
            fontSize = 36.sp, fontWeight = FontWeight.Black,
            fontFamily = FontFamily.Monospace,
        )
        Text(
            if (coherent) "THE A.S.S. IS COHERENT" else "SOME PIPES ARE SILENT",
            color = if (coherent) Color(0xFF4a9a44) else Color(0xFF667),
            fontSize = 14.sp, fontFamily = FontFamily.Monospace, letterSpacing = 2.sp,
        )

        Spacer(Modifier.height(24.dp))
        Text("Score: ${state.score}",
            color = Color(0xFF8cc88a), fontSize = 18.sp,
            fontFamily = FontFamily.Monospace)
        if (coherent) {
            Text("2 × 3 × 5 × 7 × 11 × 13 × 17 = 510,510",
                color = Color(0xFFd4a94a), fontSize = 10.sp,
                fontFamily = FontFamily.Monospace)
        }

        Spacer(Modifier.height(16.dp))
        Text("${state.answered} scenarios · ${state.streak} final streak",
            color = Color(0xFF556), fontSize = 10.sp, fontFamily = FontFamily.Monospace)

        Spacer(Modifier.height(24.dp))
        Text(
            if (coherent)
                "You broke every rule.\nAll seven pipes carry signal.\nThe infrastructure works."
            else
                "Some rules held you back.\nSome pipes went quiet.\nTry again — speak with intent.",
            color = Color(0xFF889), fontSize = 11.sp, lineHeight = 18.sp,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(8.dp))
        Text("something stinks?\ngood. that means the system is working.",
            color = Color(0xFF334), fontSize = 9.sp, fontFamily = FontFamily.Monospace,
            textAlign = TextAlign.Center)

        Spacer(Modifier.height(32.dp))
        Box(Modifier.clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF4a9a44)).clickable { onRestart() }
            .padding(horizontal = 24.dp, vertical = 10.dp)) {
            Text("PLAY AGAIN", color = Color.White, fontSize = 11.sp,
                fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
        }
    }
}
