package org.izaki.parsers

import org.izaki.dataholder.RouteRaw

public fun parseRoutes(routesCsvLines: List<String>): List<RouteRaw> {

    val routes = mutableListOf<RouteRaw>()

    for (routesLine in routesCsvLines) {

        if (routesLine.isNotEmpty() && routesLine.isNotBlank()) {
            val trimmedRoutesLine = routesLine.trim()
            val splittedRoutes = trimmedRoutesLine.split(",")
            if (splittedRoutes.size != 5) {
                continue
            }
            if (splittedRoutes[0].isEmpty() || splittedRoutes[1].isEmpty() || splittedRoutes[2].isEmpty() || splittedRoutes[3].isEmpty()) {
                continue
            }
            val routeRaw = parseRoutesLine(splittedRoutes)
            routes.add(routeRaw)
        }
    }
    return routes
}

fun parseRoutesLine(routesTokens: List<String>): RouteRaw {
    val id = routesTokens[0].trim().uppercase()
    val originHubId = routesTokens[1].trim().uppercase()
    val destinationHubId = routesTokens[2].trim().uppercase()
    val distanceKm = routesTokens[3]
    val typicalDelayMin = routesTokens[4]
    return RouteRaw(id, originHubId , destinationHubId, distanceKm, typicalDelayMin)
}