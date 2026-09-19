package org.izaki

import org.izaki.quick_sort.quickSortPackages
import org.izaki.CsvRepositories.CsvPackagesRepository
import org.izaki.CsvRepositories.CsvRouteRepository
import org.izaki.CsvRepositories.CsvVehicleRepository
import org.izaki.CsvRepositories.CsvWarehouseRepository

fun main() {
    val csvPackagesRepository = CsvPackagesRepository(
        "src/main/resources/packages.csv",
        "src/main/resources/warehouses.csv"
    )

    val csvRouteRepository = CsvRouteRepository(
        "src/main/resources/routes.csv",
        "src/main/resources/warehouses.csv",
    )

    val csvVehicleRepository = CsvVehicleRepository(
        "src/main/resources/vehicles.csv",
        "src/main/resources/warehouses.csv",
    )

    val csvWarehouseRepository = CsvWarehouseRepository(
        csvPackagesRepository,
        csvVehicleRepository,
        csvRouteRepository,
        "src/main/resources/warehouses.csv"
    )

    val sortedPackages = quickSortPackages(csvPackagesRepository.getAll())

    sortedPackages.forEach {
        println(it)
    }
}