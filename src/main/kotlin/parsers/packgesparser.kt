package org.izaki.parsers

import org.izaki.dataholder.PackagePriority
import org.izaki.dataholder.PackageRaw
import java.io.File

public fun parsePackages(packagesCsvLines: List<String>): List<PackageRaw> {

    val packages = mutableListOf<PackageRaw>()

    for (packageLine in packagesCsvLines) {

        if (packageLine.isNotEmpty() && packageLine.isNotBlank()) {
            val trimmedPackageLine = packageLine.trim()
            val splittedPackage = trimmedPackageLine.split(",")
            if (splittedPackage.size != 5) {
                continue
            }

            if (splittedPackage[0].isEmpty()) {
                continue
            }
            if (splittedPackage[2].isEmpty() || splittedPackage[3].isEmpty()) {
                continue
            }
            val packageRaw = parsePackageLine(splittedPackage)
            packages.add(packageRaw)
        }
    }

    return packages
}

fun loadCsvFile(filePath: String): List<String> {
    val file = File(filePath)
    return file.readLines().drop(1)
}

fun parsePackageLine(packageTokens: List<String>): PackageRaw {
    val id = parsePackegeId(packageTokens[0])
    val weight = parsePackageWeight(packageTokens[1])
    val originHubId = packageTokens[2].trim().uppercase()
    val destinationHubId = packageTokens[3].trim().uppercase()
    val priority = parsePackagePriority(packageTokens[4])
    return PackageRaw(id, weight, originHubId, destinationHubId, priority)
}

private fun parsePackegeId(packageToken: String): String {
    return packageToken.trim().uppercase()
}

private fun parsePackageWeight(splittedPackage: String): Float {
    return if (splittedPackage.toFloatOrNull() == null) 0f else splittedPackage.toFloat()
}

private fun parsePackagePriority(splittedPackage: String): PackagePriority {
    val priorityString = splittedPackage.trim().uppercase()
    val safePriorityString = if (priorityString !in listOf("LOW", "URGENT", "STANDARD")) "LOW" else priorityString
    val priority = PackagePriority.valueOf(safePriorityString)
    return priority
}


