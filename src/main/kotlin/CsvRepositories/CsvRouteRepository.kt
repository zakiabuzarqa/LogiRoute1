package org.izaki.CsvRepositories

import org.izaki.domain.model.LogisticsPackage
import org.izaki.domain.model.Route
import org.izaki.domain.model.Warehouse
import org.izaki.parsers.loadCsvFile
import org.izaki.parsers.parsePackages
import org.izaki.parsers.parseRoutes
import org.izaki.parsers.parsewarehouses
import org.izaki.repositories.RouteRepository

class CsvRouteRepository(
    private val routeFilePath: String,
    private val warehouseFilePath: String
) : RouteRepository {

    override fun getAll(): List<Route> {
        val loadedCsvLines = loadCsvFile(routeFilePath)
        val warehousesCsvLines = loadCsvFile(warehouseFilePath)

        val rawRoute = parseRoutes(loadedCsvLines)
        val rawWarehouse = parsewarehouses(warehousesCsvLines)

        val domainWarehouses = rawWarehouse.map { warehouseRaw ->
            Warehouse(
                warehouseRaw.id,
                warehouseRaw.name,
                warehouseRaw.regionalZone,
            )
        }

        val warehouseTable = domainWarehouses.associateBy { warehouse -> warehouse.id }

        return rawRoute.filter { routeRaw ->
            val currentOriginHub = warehouseTable[routeRaw.originHubId]
            val currentDestinationHub = warehouseTable[routeRaw.destinationHubId]
            currentOriginHub != null && currentDestinationHub != null
        }.map { routeRaw ->
            Route(
                routeRaw.routeId,
                routeRaw.distanceKm.toFloat(),
                routeRaw.typicalDelayMin.toFloat(),
                warehouseTable[routeRaw.originHubId]!!,
                warehouseTable[routeRaw.destinationHubId]!!,

            )
        }


    }
}
