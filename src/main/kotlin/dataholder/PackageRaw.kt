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