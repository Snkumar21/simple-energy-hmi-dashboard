package com.example.hmiapp

import com.example.hmiapp.data.SimulatedVehicleDataSource
import com.example.hmiapp.model.VehicleState
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeViewModelTest {

    @Test
    fun initialVehicleStateIsParked() {
        val state = VehicleState()

        assertEquals(0, state.speed)
        assertEquals(false, state.motorOn)
        assertEquals(false, state.killSwitchActive)
    }

    @Test
    fun simulationEmitsFiveExpectedScenarios() = runBlocking {
        val dataSource = SimulatedVehicleDataSource()

        val states = dataSource
            .observeVehicleState()
            .take(5)
            .toList()

        assertEquals(5, states.size)

        assertEquals(0, states[0].speed)
        assertEquals(false, states[0].motorOn)

        assertEquals(true, states[1].motorOn)

        assertEquals(25, states[2].speed)

        assertEquals(50, states[3].speed)

        assertEquals(true, states[4].killSwitchActive)
        assertEquals(false, states[4].motorOn)
    }
}