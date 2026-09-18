package org.izaki

import org.izaki.domain.DomainGraphBuilder
import org.izaki.parsers.loadCsvFile
import org.izaki.parsers.parsePackages
import org.izaki.parsers.parseRoutes
import org.izaki.parsers.parsefleet
import org.izaki.parsers.parsewarehouses
import org.izaki.quick_sort.quickSortPackages
import org.izaki.repositories.CsvPackagesRepository

fun main() {
    val packagesCsvLines = loadCsvFile("src/main/resources/packages.csv")
    val fleetCsvLines = loadCsvFile("src/main/resources/fleet.csv")
    val routesCsvLines = loadCsvFile("src/main/resources/routes.csv")
    val warehousesCsvLines = loadCsvFile("src/main/resources/warehouses.csv")

    val packageRawList = parsePackages(packagesCsvLines)
    val fleetRawList = parsefleet(fleetCsvLines)
    val routeRawList = parseRoutes(routesCsvLines)
    val warehouseRawList = parsewarehouses(warehousesCsvLines)

    val domainGraphBuilder = DomainGraphBuilder()

    val domainGraph = domainGraphBuilder.buildWarehouseGraph(
        warehouseRawList,
        packageRawList,
        routeRawList,
        fleetRawList
    )

    val csvPackagesRepository = CsvPackagesRepository(
        "src/main/resources/packages.csv",
        "src/main/resources/warehouses.csv"
    )

    val sortedPackages = quickSortPackages(csvPackagesRepository.getAll())

    sortedPackages.forEach {
        println(it)
    }
}