package org.izaki.domain.model

import org.izaki.dataholder.PackagePriority

data class LogisticsPackage(
    val id: String,
    val priority: PackagePriority,
    val originHub: Warehouse,
    val destinationHub: Warehouse,
    val weight: Float,
)