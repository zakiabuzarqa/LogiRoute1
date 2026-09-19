package org.izaki.repositories

import org.izaki.domain.model.Vehicle

interface VehicleRepository {
    fun getAll(): List<Vehicle>
}