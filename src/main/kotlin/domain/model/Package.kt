package org.izaki.domain.model

import org.izaki.dataholder.PackagePriority

data class Package(
    val id: String,
    val name: String,
    val priority: PackagePriority,
    val originHub: Warehouse,
    val destinationHub: Warehouse,
    val weight: Double,
)