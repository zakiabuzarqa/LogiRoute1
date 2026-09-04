package org.izaki.domain
import org.izaki.dataholder.FleetRaw
import org.izaki.dataholder.PackageRaw
import org.izaki.dataholder.RouteRaw
import org.izaki.dataholder.WarehouseRaw
import org.izaki.domain.model.LogisticsPackage
import org.izaki.domain.model.Route
import org.izaki.domain.model.Vehicle
import org.izaki.domain.model.Warehouse

class DomainGraphBuilder {

    fun buildWarehouseGraph(
        rawWarehouse: List<WarehouseRaw>,
        rawPackages: List<PackageRaw>,
        rawRoutes: List<RouteRaw>,
        rawVehicle: List<FleetRaw>,
    ): DomainGraph {
        val domainWarehouses = rawWarehouse.map { warehouseRaw ->
            Warehouse(
                warehouseRaw.id,
                warehouseRaw.name,
                warehouseRaw.regionalZone,
            )
        }

        val warehouseTable = domainWarehouses.associateBy { warehouse -> warehouse.id }

        val domainVehicle =rawVehicle.map { vehicleRaw ->
            warehouseTable[vehicleRaw.currentHubId]?.let { warehouse ->
                Vehicle(
                    vehicleRaw.vehicleId,
                    vehicleRaw.maxCapacityKg,
                    vehicleRaw.costPerKm,
                    warehouse,
                )
            }
        }

        val groupedVehicleMap = domainVehicle.groupBy {
            it?.currentHub?.id
        }

        val domainPackages = rawPackages.map { packageRaw ->
            val currentOriginHub = warehouseTable[packageRaw.originHubId]
            val currentDestinationHub = warehouseTable[packageRaw.destinationHubId]
            return@map if (currentOriginHub != null && currentDestinationHub  != null) {
                LogisticsPackage(
                    packageRaw.id,
                    packageRaw.priority,
                    currentOriginHub,
                    currentDestinationHub,
                    packageRaw.weight
                )
            } else {
                return@map null
            }
        }

        val groupedPackagesMap = domainPackages.groupBy { logisticsPackage ->
            logisticsPackage?.originHub?.id
        }

        val domainRoutes =rawRoutes.map { routeRaw ->

            val currentOriginHub = warehouseTable[routeRaw.originHubId]
            val currentDestinationHub = warehouseTable[routeRaw.destinationHubId]

            return@map if (currentOriginHub != null && currentDestinationHub  != null){
                Route(
                    routeRaw.routeId,
                    routeRaw.distanceKm,
                    routeRaw.typicalDelayMin,
                    currentOriginHub,
                    currentDestinationHub,
                )
            }else{
                return@map null
            }


        }
        val groupedRoutesMap = domainRoutes.groupBy {
            it?.originHub?.id
        }
        domainWarehouses.forEach { warehouse ->
            val currentWarehousePackages = groupedPackagesMap[warehouse.id] ?: emptyList()
            warehouse.addPackages(currentWarehousePackages.filterNotNull())
            val currentWarehouseRoutes = groupedRoutesMap[warehouse.id] ?: emptyList()
            warehouse.addRoutes(currentWarehouseRoutes.filterNotNull())
            val currentWarehouseVehicle = groupedVehicleMap[warehouse.id] ?: emptyList()
            warehouse.addVehicle(currentWarehouseVehicle.filterNotNull())
        }



        return DomainGraph(
            warehouses = domainWarehouses,
            packages = domainPackages.filterNotNull(),
            route = domainRoutes.filterNotNull(),
            vehicles = domainVehicle.filterNotNull()
        )
    }

}

data class DomainGraph(
    val warehouses: List<Warehouse>,
    val packages: List<LogisticsPackage>,
    val route: List<Route>,
    val vehicles: List<Vehicle>,
)