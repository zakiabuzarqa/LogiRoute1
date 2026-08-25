package org.izaki

import org.izaki.dataholder.PackagePriority
import org.izaki.dataholder.PackageRaw
import org.izaki.dataholder.WarehouseRaw
import org.izaki.dataholder.warehousesregionalZone

import org.izaki.parsers.loadCsvFile
import org.izaki.parsers.parsePackages
import org.izaki.parsers.parseRoutes
import org.izaki.parsers.parsefleet
import org.izaki.parsers.parsewarehouses

fun main() {
    val packagesCsvLines = loadCsvFile("src/main/resources/packages.csv")
    val fleetCsvLines = loadCsvFile("src/main/resources/fleet.csv")
    val routesCsvLines = loadCsvFile("src/main/resources/routes.csv")
    val warehousesCsvLines = loadCsvFile("src/main/resources/warehouses.csv")

    val packageRawList = parsePackages(packagesCsvLines)
    val fleetRawList = parsefleet(fleetCsvLines)
    val routeRawList = parseRoutes(routesCsvLines)
    val warehouseRawList = parsewarehouses(warehousesCsvLines)
}