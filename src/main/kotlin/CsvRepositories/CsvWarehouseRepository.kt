package org.izaki.CsvRepositories

import org.izaki.domain.model.Warehouse
import org.izaki.parsers.loadCsvFile
import org.izaki.parsers.parsewarehouses
import org.izaki.repositories.PackageRepository
import org.izaki.repositories.WarehouseRepository

class CsvWarehouseRepository(
    private val packageRepository: PackageRepository,
    private val vehicleRepository: CsvVehicleRepository,
    private val routeRepository: CsvRouteRepository,
    private val warehouseFilePath: String
): WarehouseRepository {

    override fun getAll(): List<Warehouse> {

        val warehousesCsvLines = loadCsvFile(warehouseFilePath)
        val warehouseRawList = parsewarehouses(warehousesCsvLines)

        val domainWarehouses = warehouseRawList.map { warehouseRaw ->
            Warehouse(
                warehouseRaw.id,
                warehouseRaw.name,
                warehouseRaw.regionalZone,
            )
        }

        val cargoQueue = packageRepository.getAll().groupBy { it.originHub }
        val vehicleList = vehicleRepository.getAll().groupBy { it.currentHub }
        val routesList = routeRepository.getAll().groupBy { it.routeId }

        domainWarehouses.forEach { domainWarehouse ->
            val currentHubCargoQueue = cargoQueue[domainWarehouse] ?: emptyList()
            domainWarehouse.addPackages(currentHubCargoQueue)
            domainWarehouse.addVehicle(vehicleList[domainWarehouse] ?: emptyList())
            domainWarehouse.addRoutes(routesList[domainWarehouse.id] ?: emptyList())
        }

        return domainWarehouses
    }

}