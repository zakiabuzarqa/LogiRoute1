package org.izaki.CsvRepositories
import org.izaki.domain.model.Vehicle
import org.izaki.domain.model.Warehouse
import org.izaki.parsers.loadCsvFile
import org.izaki.parsers.parsefleet
import org.izaki.parsers.parsewarehouses
import org.izaki.repositories.VehicleRepository

class CsvVehicleRepository (
    private val  vehicleFilePath:String,
    private val warehouseFilePath:String,
): VehicleRepository {
    override fun getAll(): List<Vehicle> {
        val loadedCsvLines = loadCsvFile(vehicleFilePath)
        val warehousesCsvLines = loadCsvFile(warehouseFilePath)

        val rawVehicle = parsefleet(loadedCsvLines)
        val rawWarehouse = parsewarehouses(warehousesCsvLines)

        val domainWarehouses = rawWarehouse.map { warehouseRaw ->
            Warehouse(
                warehouseRaw.id,
                warehouseRaw.name,
                warehouseRaw.regionalZone,
            )
        }

        val warehouseTable = domainWarehouses.associateBy { warehouse -> warehouse.id }

        return rawVehicle.filter { vehicleRaw ->
            val currentHub = warehouseTable[vehicleRaw.currentHubId]
            currentHub != null
        }.map { vehicleRaw ->
            Vehicle(
                vehicleRaw.vehicleId,
                vehicleRaw.maxCapacityKg.toFloat(),
                vehicleRaw.costPerKm.toFloat(),
                warehouseTable[vehicleRaw.currentHubId]!!,
            )
        }

    }
}