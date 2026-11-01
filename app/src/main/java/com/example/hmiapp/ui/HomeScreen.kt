
package com.example.hmiapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hmiapp.ui.components.KillSwitchIndicator
import com.example.hmiapp.ui.components.MotorStatusCard
import com.example.hmiapp.ui.components.Speedometer
import com.example.hmiapp.viewmodel.HomeViewModel

private val Background = Color(0xFF101319)
private val Orange = Color(0xFFFF8A00)
private val Muted = Color(0xFF9299A5)

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {

    val vehicleState by viewModel.vehicleState.collectAsStateWithLifecycle()

    val scenario = when {
        vehicleState.killSwitchActive -> "MOTOR DISABLED"
        vehicleState.speed >= 50 -> "ACCELERATING"
        vehicleState.speed > 0 -> "RIDING"
        vehicleState.motorOn -> "READY"
        else -> "PARKED"
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 38.dp, vertical = 22.dp)
        ) {

            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        "SIMPLE ENERGY",
                        color = Orange,
                        fontSize = 12.sp,
                        letterSpacing = 3.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        "Vehicle Dashboard",
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(Color(0xFF20C77A), RoundedCornerShape(50))
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        "LIVE SIMULATION",
                        color = Color(0xFF20C77A),
                        fontSize = 11.sp,
                        letterSpacing = 1.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Main dashboard
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(42.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Speedometer section
                Column(
                    modifier = Modifier
                        .weight(1.1f)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Speedometer(
                        speed = vehicleState.speed
                    )
                }

                // Status section
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        "VEHICLE STATUS",
                        color = Muted,
                        fontSize = 11.sp,
                        letterSpacing = 2.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    MotorStatusCard(
                        motorOn = vehicleState.motorOn
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    KillSwitchIndicator(
                        isActive = vehicleState.killSwitchActive
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Driving mode
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Color(0xFF202630),
                                RoundedCornerShape(20.dp)
                            )
                            .padding(20.dp)
                    ) {
                        Text(
                            "CURRENT DRIVING MODE",
                            color = Muted,
                            fontSize = 11.sp,
                            letterSpacing = 1.5.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            scenario,
                            color = Orange,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
