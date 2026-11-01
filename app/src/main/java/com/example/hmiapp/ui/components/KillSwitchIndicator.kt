
package com.example.hmiapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KillSwitchIndicator(
    isActive: Boolean,
    modifier: Modifier = Modifier
) {
    val red = Color(0xFFFF5757)
    val green = Color(0xFF20C77A)

    val statusColor = if (isActive) red else green

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF202630)
        )
    ) {
        Row(
            modifier = Modifier.padding(22.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    "SAFETY SYSTEM",
                    color = Color(0xFF9299A5),
                    fontSize = 11.sp,
                    letterSpacing = 1.5.sp
                )

                Spacer(modifier = Modifier.height(9.dp))

                Text(
                    if (isActive) "KILL ACTIVE" else "KILL INACTIVE",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    if (isActive) "Motor disabled" else "Safety system normal",
                    color = Color(0xFF9299A5),
                    fontSize = 12.sp
                )
            }

            Box(
                modifier = Modifier
                    .size(17.dp)
                    .background(statusColor, CircleShape)
            )
        }
    }
}
