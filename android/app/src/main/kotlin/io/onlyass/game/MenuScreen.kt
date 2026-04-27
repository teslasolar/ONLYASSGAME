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
fun MenuScreen(onStart: () -> Unit) {
    Column(
        Modifier.fillMaxSize().background(Color(0xFF040408)).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("ONLY", color = Color(0xFF4a9a44), fontSize = 32.sp,
            fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
        Text("A.S.S.", color = Color(0xFF9b3da0), fontSize = 48.sp,
            fontWeight = FontWeight.Black, fontFamily = FontFamily.Monospace)
        Spacer(Modifier.height(8.dp))
        Text("BREAK A RULE", color = Color(0xFF8cc88a), fontSize = 14.sp,
            fontFamily = FontFamily.Monospace, letterSpacing = 4.sp)
        Spacer(Modifier.height(24.dp))
        Text("7 pipes · 7 rules · 1 truth",
            color = Color(0xFF556), fontSize = 10.sp, fontFamily = FontFamily.Monospace)
        Text("the smart move is the one they said not to make",
            color = Color(0xFF445), fontSize = 9.sp, fontFamily = FontFamily.Monospace,
            textAlign = TextAlign.Center)
        Spacer(Modifier.height(32.dp))

        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            PIPES.forEach { p ->
                Box(Modifier.size(24.dp).padding(2.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(p.color))) {
                    Text(p.glyph, Modifier.align(Alignment.Center),
                        color = Color.White, fontSize = 10.sp)
                }
            }
        }
        Spacer(Modifier.height(8.dp))
        Text("2 × 3 × 5 × 7 × 11 × 13 × 17 = 510,510",
            color = Color(0xFF334), fontSize = 8.sp, fontFamily = FontFamily.Monospace)
        Spacer(Modifier.height(32.dp))

        Box(Modifier.clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF4a9a44)).clickable { onStart() }
            .padding(horizontal = 32.dp, vertical = 12.dp)) {
            Text("LIFT THE COVER", color = Color.White, fontSize = 12.sp,
                fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
        }
    }
}
