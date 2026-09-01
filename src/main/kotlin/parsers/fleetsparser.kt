package org.izaki.parsers
import org.izaki.dataholder.PackagePriority
import org.izaki.dataholder.FleetRaw


public fun parsefleet(fleetsCsvLines: List<String>): MutableList<FleetRaw> {

    val fleets = mutableListOf<FleetRaw>()

    for (fleetLine in fleetsCsvLines) {

        if (fleetLine.isNotEmpty() && fleetLine.isNotBlank()) {
            val trimmedfleetLine = fleetLine.trim()
            val splittedfleet = trimmedfleetLine.split(",")
            if (splittedfleet.size != 4) {
                continue
            }

            if (splittedfleet[2].isEmpty() || splittedfleet[3].isEmpty()||splittedfleet[0].isEmpty()||splittedfleet[1].isEmpty()) {
                continue
            }
            val fleetRaw = parsefleetLine(splittedfleet)
            fleets.add(fleetRaw)

        }
    }

    return fleets
}
fun parsefleetLine(fleetTokens: List<String>): FleetRaw {
    val id = parseFleetId(fleetTokens[0])
    val  currentHubId= parseCurrentId(fleetTokens[1])
    val maxCapacityKg = fleetTokens[2]
    val costPerKm = fleetTokens[3]
    return FleetRaw(id, currentHubId, maxCapacityKg, costPerKm)
}

private fun parseFleetId(packageToken: String): String {
    return packageToken.trim().uppercase()
}

private fun parseCurrentId(packageToken: String): String {
    return packageToken.trim().uppercase()
}

private fun parsePackagePriority(splittedPackage: String): PackagePriority {
    val priorityString = splittedPackage.trim().uppercase()
    val safePriorityString = if (priorityString !in listOf("LOW", "HIGH", "STANDARD")) "LOW" else priorityString
    val priority = PackagePriority.valueOf(safePriorityString)
    return priority
}