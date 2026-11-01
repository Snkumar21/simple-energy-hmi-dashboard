package com.example.hmiapp.model

data class VehicleState(
    val speed: Int = 0,
    val motorOn: Boolean = false,
    val killSwitchActive: Boolean = false
)