package com.example.hmiapp.data

import com.example.hmiapp.model.VehicleState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SimulatedVehicleDataSource {

    private val scenarios = listOf(
        // 1. Parked
        VehicleState(0, false, false),

        // 2. Ready
        VehicleState(0, true, false),

        // 3. Riding
        VehicleState(25, true, false),

        // 4. Accelerating
        VehicleState(50, true, false),

        // 5. Motor Disabled
        VehicleState(0, false, true)
    )

    fun observeVehicleState(): Flow<VehicleState> = flow {

        var index = 0

        while (true) {
            emit(scenarios[index])

            index = (index + 1) % scenarios.size

            delay(1000)
        }
    }
}