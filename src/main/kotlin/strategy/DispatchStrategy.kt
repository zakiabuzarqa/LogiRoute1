package org.izaki.strategy

import org.izaki.domain.model.LogisticsPackage

interface DispatchStrategy {
    fun calculateTransitCost(logisticsPackage: LogisticsPackage, distance: Int): Float
    fun getPriorityMultiplier(logisticsPackage: LogisticsPackage, distance: Int): Float
}