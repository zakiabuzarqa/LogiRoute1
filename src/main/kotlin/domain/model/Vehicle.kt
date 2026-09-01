package org.izaki.domain.model


data class Vehicle(
    val vehicleId: String,
    val maxCapacityKg: String,
    val costPerKm: String,
    val currentHub: Warehouse
)