package org.izaki.domain.model

data class Route (
    val routeId: String,
    val distanceKm: String,
    val typicalDelayMin: String,
    val originHub: Warehouse,
    val destinationHub: Warehouse
)