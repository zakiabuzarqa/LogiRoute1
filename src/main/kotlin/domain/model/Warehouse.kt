package org.izaki.domain.model

import org.izaki.dataholder.RegionalZone
import org.izaki.domain.model.Package

data class Warehouse(
    val id: String,
    val name: String,
    val regionalZone: RegionalZone,
    private val cargoQueue: MutableList<Package> = mutableListOf(),
    private val outgoingRoutes: MutableList<Route> = mutableListOf(),
    private val stationedVehicles: MutableList<Vehicle> = mutableListOf(),
){
    fun addPackage(packages: Package) {
        cargoQueue.add(packages)
    }

    fun addPackages(packages: List<Package>) {
        cargoQueue.addAll(packages)
    }

    fun addRoute(route: Route) {
        outgoingRoutes.add(route)
    }

    fun addRoutes(route: List<Route>) {
        outgoingRoutes.addAll(route)
    }

    fun addVehicle(vehicle: Vehicle) {
        stationedVehicles.add(vehicle)
    }

    fun addVehicles(vehicle: Vehicle) {
        stationedVehicles.add(vehicle)
    }
}