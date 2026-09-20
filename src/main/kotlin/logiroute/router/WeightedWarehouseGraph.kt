package org.izaki.logiroute.router

data class RouteEdge(
    val destination: String,
    val distanceKm: Double
)

class WeightedWarehouseGraph {
    private val adjacencyList: MutableMap<String, MutableList<RouteEdge>> = mutableMapOf()

    fun addConnection(warehouseA: String, warehouseB: String, distanceKm: Double) {
        adjacencyList.computeIfAbsent(warehouseA) { mutableListOf() }.add(RouteEdge(warehouseB, distanceKm))
        adjacencyList.computeIfAbsent(warehouseB) { mutableListOf() }.add(RouteEdge(warehouseA, distanceKm))
    }

    fun getNeighbors(warehouseId: String): List<RouteEdge> {
        return adjacencyList[warehouseId] ?: emptyList()
    }

    fun getAllWarehouses(): Set<String> {
        return adjacencyList.keys
    }
}
