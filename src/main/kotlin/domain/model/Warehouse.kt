package org.izaki.domain.model

import org.izaki.dataholder.RegionalZone

data class Warehouse(
    val id: String,
    val name: String,
    val regionalZone: RegionalZone,
    private val cargoQueue: MutableList<LogisticsPackage> = mutableListOf(),
    private val outgoingRoutes: MutableList<Route> = mutableListOf(),
    private val stationedVehicles: MutableList<Vehicle> = mutableListOf(),
){

    override fun toString(): String {
        return id
    }

    fun addPackage(packages: LogisticsPackage) {
        cargoQueue.add(packages)
    }

    fun addPackages(logisticsPackages: List<LogisticsPackage>) {
        cargoQueue.addAll(logisticsPackages)
    }

    fun addRoute(route: Route) {
        outgoingRoutes.add(route)
    }

    fun addRoutes(route: List<Route>) {
        outgoingRoutes.addAll(route)
    }

    fun addVehicle(vehicle: List<Vehicle>) {
        stationedVehicles.addAll(vehicle)
    }

    fun addVehicles(vehicle: Vehicle) {
        stationedVehicles.add(vehicle)
    }
}