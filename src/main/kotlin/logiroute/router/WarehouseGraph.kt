package org.izaki.logiroute.router

class WarehouseGraph {
    private val adjacencyList: MutableMap<String, MutableList<String>> = mutableMapOf()

    fun addConnection(warehouseA: String, warehouseB: String) {
        adjacencyList.computeIfAbsent(warehouseA) { mutableListOf() }.add(warehouseB)
        adjacencyList.computeIfAbsent(warehouseB) { mutableListOf() }.add(warehouseA)
    }
    fun getNeighbors(warehouseId: String): List<String> {
        return adjacencyList[warehouseId] ?: emptyList()
    }
}