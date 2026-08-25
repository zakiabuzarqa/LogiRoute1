package org.izaki

import org.izaki.dataholder.RouteRaw
import org.izaki.parsers.loadCsvFile
import org.izaki.parsers.parsePackages
import org.izaki.parsers.parseRoutes
import org.izaki.parsers.parsefleet

fun main() {
    val packagesCsvLines = loadCsvFile("src/main/resources/packages.csv")
    val fleetCsvLines = loadCsvFile("src/main/resources/fleet.csv")
    val routesCsvLines = loadCsvFile("src/main/resources/routes.csv")
    val warehousesCsvLines = loadCsvFile("src/main/resources/warehouses.csv")

    val packageRawList = parsePackages(packagesCsvLines)
    val fleetRawList = parsefleet(fleetCsvLines)
    val routeRawList = parseRoutes(routesCsvLines)
}