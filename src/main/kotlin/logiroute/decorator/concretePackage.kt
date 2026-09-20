package org.izaki.logiroute.decorator

data class concretePackage (
    val trackingId: String,
    val weightKg: Double,
    val baseRate: Double
):PackageComponent {
    override fun calculateRate(): Double = baseRate
}
