
package com.example.hmiapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Orange = Color(0xFFFF8A00)
private val Track = Color(0xFF303640)
private val Muted = Color(0xFF9299A5)

@Composable
fun Speedometer(speed: Int, modifier: Modifier = Modifier) {

    val safeSpeed = speed.coerceIn(0, 100)
    val progress = safeSpeed / 100f

    Box(
        modifier = modifier.size(290.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {

            val stroke = 16.dp.toPx()

            drawArc(
                color = Track,
                startAngle = 135f,
                sweepAngle = 270f,
                useCenter = false,
                style = Stroke(stroke, cap = StrokeCap.Round)
            )

            drawArc(
                color = Orange,
                startAngle = 135f,
                sweepAngle = 270f * progress,
                useCenter = false,
                style = Stroke(stroke, cap = StrokeCap.Round)
            )

            // Speedometer tick marks
            for (i in 0..10) {
                val angle = Math.toRadians((135 + i * 27).toDouble())
                val radius = size.minDimension / 2 - 5.dp.toPx()

                val start = Offset(
                    x = center.x + kotlin.math.cos(angle).toFloat() * radius,
                    y = center.y + kotlin.math.sin(angle).toFloat() * radius
                )

                val end = Offset(
                    x = center.x + kotlin.math.cos(angle).toFloat() * (radius - 9.dp.toPx()),
                    y = center.y + kotlin.math.sin(angle).toFloat() * (radius - 9.dp.toPx())
                )

                drawLine(
                    color = Color.LightGray.copy(alpha = 0.55f),
                    start = start,
                    end = end,
                    strokeWidth = 2.dp.toPx()
                )
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = "CURRENT SPEED",
                color = Muted,
                fontSize = 11.sp,
                letterSpacing = 2.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "$safeSpeed",
                    color = Color.White,
                    fontSize = 76.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "km/h",
                    color = Orange,
                    fontSize = 17.sp,
                    modifier = Modifier.padding(bottom = 14.dp)
                )
            }

            Text(
                text = "MAX 100 KM/H",
                color = Muted,
                fontSize = 10.sp,
                letterSpacing = 1.5.sp
            )
        }
    }
}