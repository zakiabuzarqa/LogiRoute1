package org.izaki.CsvRepositories

import org.izaki.domain.model.LogisticsPackage
import org.izaki.domain.model.Warehouse
import org.izaki.parsers.loadCsvFile
import org.izaki.parsers.parsePackages
import org.izaki.parsers.parsewarehouses
import org.izaki.repositories.PackageRepository

class CsvPackagesRepository(
    private val packagesFilePath: String,
    private val warehouseFilePath: String
): PackageRepository {

    override fun getAll(): List<LogisticsPackage> {
        val loadedCsvLines = loadCsvFile(packagesFilePath)
        val warehousesCsvLines = loadCsvFile(warehouseFilePath)

        val rawPackages =  parsePackages(loadedCsvLines)
        val rawWarehouse = parsewarehouses(warehousesCsvLines)

        val domainWarehouses = rawWarehouse.map { warehouseRaw ->
            Warehouse(
                warehouseRaw.id,
                warehouseRaw.name,
                warehouseRaw.regionalZone,
            )
        }

        val warehouseTable = domainWarehouses.associateBy { warehouse -> warehouse.id }

        return rawPackages.filter { packageRaw ->
            val currentOriginHub = warehouseTable[packageRaw.originHubId]
            val currentDestinationHub = warehouseTable[packageRaw.destinationHubId]
            currentOriginHub != null && currentDestinationHub  != null
        }.map { packageRaw ->
            LogisticsPackage(
                packageRaw.id,
                packageRaw.priority,
                warehouseTable[packageRaw.originHubId]!!,
                warehouseTable[packageRaw.destinationHubId]!!,
                packageRaw.weight
            )
        }

    }
}