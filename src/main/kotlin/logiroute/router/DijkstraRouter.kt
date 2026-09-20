package org.izaki.logiroute.router
class DijkstraRouter(private val graph: WeightedWarehouseGraph) {

    fun findShortestPath(startWarehouse: String, destinationWarehouse: String): List<String> {
        if (startWarehouse == destinationWarehouse) {
            return listOf(startWarehouse)
        }
        val distances = mutableMapOf<String, Double>()
        val previousNodes = mutableMapOf<String, String?>()
        val unvisited = mutableSetOf<String>()

        for (warehouse in graph.getAllWarehouses()) {
            distances[warehouse] = Double.MAX_VALUE
            previousNodes[warehouse] = null
            unvisited.add(warehouse)
        }

        distances[startWarehouse] = 0.0

        while (unvisited.isNotEmpty()) {
            val currentWarehouse = unvisited.minByOrNull { distances[it] ?: Double.MAX_VALUE }
                ?: break

            if (distances[currentWarehouse] == Double.MAX_VALUE) {
                break
            }

            if (currentWarehouse == destinationWarehouse) {
                break
            }
            unvisited.remove(currentWarehouse)
            val currentDistance = distances[currentWarehouse] ?: Double.MAX_VALUE
            for (edge in graph.getNeighbors(currentWarehouse)) {
                if (edge.destination in unvisited) {
                    val newDistance = currentDistance + edge.distanceKm
                    if (newDistance < (distances[edge.destination] ?: Double.MAX_VALUE)) {
                        distances[edge.destination] = newDistance
                        previousNodes[edge.destination] = currentWarehouse
                    }
                }
            }
        }
        if (distances[destinationWarehouse] == Double.MAX_VALUE) {
            return emptyList()
        }

        val path = mutableListOf<String>()
        var curr: String? = destinationWarehouse
        while (curr != null) {
            path.add(0, curr)
            curr = previousNodes[curr]
        }

        return path
    }
}