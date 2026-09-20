package org.izaki.logiroute.decorator
class FragileHandlingDecorator(
    wrappedComponent: PackageComponent,
    private val flatFee: Double = 15.0
) : PackageDecorator(wrappedComponent) {
    override fun calculateRate(): Double = super.calculateRate() + flatFee
}
class ColdChainDecorator(
    wrappedComponent: PackageComponent,
    private val refrigerationMultiplier : Double = 1.2,
): PackageDecorator(wrappedComponent){
    override fun calculateRate(): Double = super.calculateRate() * refrigerationMultiplier
}
class ExpressInsuranceDecorator(
    wrappedComponent: PackageComponent,
    private val riskPremium: Double = 25.0
): PackageDecorator(wrappedComponent){
    override fun calculateRate(): Double = super.calculateRate() + riskPremium
}