package org.izaki.dataholder

enum class PackagePriority{
    LOW, HIGH, STANDARD
}

data class PackageRaw(
    val id: String,
    val weight: Float,
    val originHubId: String,
    val destinationHubId: String,
    val priority: PackagePriority
)
data class fleetRaw(
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