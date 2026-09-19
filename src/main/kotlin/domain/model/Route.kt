package org.izaki.domain.model

data class Route (
    val routeId: String,
    val distanceKm: Float,
    val typicalDelayMin: Float,
    val originHub: Warehouse,
    val destinationHub: Warehouse
)