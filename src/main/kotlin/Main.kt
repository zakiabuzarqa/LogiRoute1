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
    val sortedPackages = selectionSortPackages(packageRawList)
    println(sortedPackages)
}
fun selectionSortPackages(packages: List<PackageRaw>): List<PackageRaw> {
    val sortedList = packages.toMutableList()
    val n = sortedList.size
    fun getRank(priority: PackagePriority): Int {
        return when (priority) {
            PackagePriority.LOW -> 1
            PackagePriority.STANDARD -> 2
            PackagePriority.URGENT -> 3
        }
    }

    for (i in 0 until n - 1) {
        var minIndex = i
        for (j in i + 1 until n) {
            val currentPkg = sortedList[j]
            val minPkg = sortedList[minIndex]

            val currentRank = getRank(currentPkg.priority)
            val minRank = getRank(minPkg.priority)
            if (currentRank < minRank) {
                minIndex = j
            }
            else if (currentRank == minRank && currentPkg.weight < minPkg.weight) {
                minIndex = j
            }
        }
        if (minIndex != i) {
            val temp = sortedList[i]
            sortedList[i] = sortedList[minIndex]
            sortedList[minIndex] = temp
        }
    }

    return sortedList
}