package org.izaki

import org.izaki.dataholder.PackagePriority
import org.izaki.dataholder.PackageRaw
import org.izaki.parsers.loadCsvFile
import org.izaki.parsers.parsePackages
import java.io.File

fun main() {
    val packagesCsvLines = loadCsvFile("src/main/resources/packages.csv")
    val fleetCsvLines = loadCsvFile("src/main/resources/fleet.csv")
    val routesCsvLines = loadCsvFile("src/main/resources/routes.csv")
    val warehousesCsvLines = loadCsvFile("src/main/resources/warehouses.csv")

    val packageRawList = parsePackages(packagesCsvLines)

    println(packageRawList)
}