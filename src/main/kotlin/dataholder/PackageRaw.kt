package org.izaki.dataholder

enum class PackagePriority{
    LOW, URGENT, STANDARD
}

data class PackageRaw(
    val id: String,
    val weight: Float,
    val originHubId: String,
    val destinationHubId: String,
    val priority: PackagePriority
)
data class FleetRaw(
    val vehicleId: String,
    val currentHubId: String,
    val maxCapacityKg: String,
    val costPerKm: String
)
data class RouteRaw(
    val routeId: String,
    val originHubId: String,
    val destinationHubId: String,
    val distanceKm: String,
    val typicalDelayMin: String,
)

enum class RegionalZone {
    CENTRAL, WEST, SOUTH, EAST, NORTH
}

data class WarehouseRaw(
    val id: String,
    val name: String,
    val regionalZone: RegionalZone,
    val latitude: String,
    val longitude: String
)