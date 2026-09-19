package org.izaki.domain.model


data class Vehicle(
    val vehicleId: String,
    val maxCapacityKg: Float,
    val costPerKm: Float,
    val currentHub: Warehouse
)