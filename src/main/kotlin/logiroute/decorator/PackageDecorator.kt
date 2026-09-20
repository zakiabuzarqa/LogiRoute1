package org.izaki.logiroute.decorator

abstract class PackageDecorator(
    private val wrappedComponent: PackageComponent,
): PackageComponent{
    override fun calculateRate(): Double = wrappedComponent.calculateRate()
}