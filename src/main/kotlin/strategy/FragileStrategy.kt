package org.izaki.strategy

import org.izaki.domain.model.LogisticsPackage

class FragileStrategy: DispatchStrategy {

    override fun calculateTransitCost(logisticsPackage: LogisticsPackage, distance: Int): Float {
        return getPriorityMultiplier(logisticsPackage, distance)+ TRANSIT_COST
    }

    override fun getPriorityMultiplier(logisticsPackage: LogisticsPackage, distance: Int): Float {
        return logisticsPackage.weight * WEIGHT_MULTIPLIER + distance * COST_MULTIPLIER_PER_KM+FRAGILE_FEES
    }
    companion object{
        private const val COST_MULTIPLIER_PER_KM = 0.0001f
        private const val WEIGHT_MULTIPLIER = 0.01f
        private const val FRAGILE_FEES = 50f
        private const val TRANSIT_COST = 10
    }


}