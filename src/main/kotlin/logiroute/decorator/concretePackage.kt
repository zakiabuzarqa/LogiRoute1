package org.izaki.logiroute.decorator

data class ConcretePackage (
    val trackingId: String,
    val weightKg: Double,
    val baseRate: Double
):PackageComponent {
    override fun calculateRate(): Double = baseRate
}
