package org.izaki.strategy

class RoutePricingEngine(
    var dispatchStrategy: DispatchStrategy
) {

    fun setStrategy(strategy: DispatchStrategy) {
        dispatchStrategy = strategy
    }

}