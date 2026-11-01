package com.example.hmiapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hmiapp.data.SimulatedVehicleDataSource
import com.example.hmiapp.model.VehicleState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val dataSource = SimulatedVehicleDataSource()

    private val _vehicleState =
        MutableStateFlow(VehicleState())

    val vehicleState: StateFlow<VehicleState> =
        _vehicleState.asStateFlow()

    init {
        startSimulation()
    }

    private fun startSimulation() {
        viewModelScope.launch {
            dataSource.observeVehicleState().collect { newState ->

                // Edge case: prevent invalid speed values
                val safeSpeed = newState.speed.coerceIn(0, 100)

                _vehicleState.value = newState.copy(
                    speed = safeSpeed
                )
            }
        }
    }
}