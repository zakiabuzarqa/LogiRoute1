package org.izaki.parsers

import org.izaki.dataholder.WarehouseRaw
import org.izaki.dataholder.warehousesregionalZone


public fun parsewarehouses(warehousesCsvLines: List<String>): MutableList<WarehouseRaw> {

    val warehouses = mutableListOf<WarehouseRaw>()

    for (warehouseLine in warehousesCsvLines) {

        if (warehouseLine.isNotEmpty() && warehouseLine.isNotBlank()) {
            val trimmedwarehouseLine = warehouseLine.trim()
            val splittedwarehouse = trimmedwarehouseLine.split(",")
            if (splittedwarehouse.size != 5) {
                continue
            }

            if (splittedwarehouse[0].isEmpty() || splittedwarehouse[1].isEmpty() || splittedwarehouse[2].isEmpty() || splittedwarehouse[3].isEmpty() || splittedwarehouse[4].isEmpty()) {
                continue
            }
            val warehouseRaw = parsewarehouseLine(splittedwarehouse)
            warehouses.add(warehouseRaw)
        }
    }

    return warehouses
}
//id,name,regionalZone,latitude,longitude
//WH-001,Hub-001,CENTRAL,37.91,-88.46
//WH-002,Hub-002,WEST,29.64,-99.44
fun parsewarehouseLine(warehouseTokens: List<String>): WarehouseRaw {
    val id = warehouseTokens[0].trim().uppercase()
    val name = warehouseTokens[1].trim().uppercase()
    val warehouseregionalZone = parsewarehousesregionalZone(warehouseTokens[2])
    val latitude =  warehouseTokens[3]
    val longitude = warehouseTokens[4]
    return WarehouseRaw(id, name, warehouseregionalZone , latitude,longitude )
}

private fun parsewarehousesregionalZone(regionalZoneString: String): warehousesregionalZone {
    val upperZone = regionalZoneString.trim().uppercase()
    val saferegionalZoneString = if (upperZone !in listOf("CENTRAL", "WEST", "SOUTH", "EAST", "NORTH")) "CENTRAL"
    else upperZone
    return warehousesregionalZone.valueOf(saferegionalZoneString)
}

