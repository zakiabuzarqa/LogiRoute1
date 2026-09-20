package org.izaki.logiroute.router


class LeastHopRouter(private val graph: WarehouseGraph) {

    fun findRoute(startWarehouse: String, destinationWarehouse: String): List<String> {
        if (startWarehouse == destinationWarehouse) {
            return listOf(startWarehouse)
        }

        val queue = ArrayDeque<List<String>>()
        val visited = mutableSetOf<String>()
        queue.add(listOf(startWarehouse))
        visited.add(startWarehouse)

        while (queue.isNotEmpty()) {
            val currentPath = queue.removeFirst()
            val currentWarehouse = currentPath.last()

            for (neighbor in graph.getNeighbors(currentWarehouse)) {
                if (neighbor == destinationWarehouse) {
                    return currentPath + neighbor
                }

                if (neighbor !in visited) {
                    visited.add(neighbor)
                    queue.add(currentPath + neighbor)
                }
            }
        }

        return emptyList()
    }
}
