package org.izaki.selectionsort

import org.izaki.dataholder.PackagePriority
import org.izaki.domain.model.LogisticsPackage


fun getRank(priority: PackagePriority): Int {
    return when (priority) {
        PackagePriority.LOW -> 3
        PackagePriority.STANDARD -> 2
        PackagePriority.URGENT -> 1
    }
}


fun selectionSortPackages(logisticsPackages: List<LogisticsPackage>): List<LogisticsPackage> {
    val sortedList = logisticsPackages.toMutableList()
    val n = sortedList.size

    for (i in 0 until n - 1) {
        var minimumPackagePriorityIndex = i
        for (j in i + 1 until n) {
            val currentPkg = sortedList[j]
            val minPkg = sortedList[minimumPackagePriorityIndex]

            val currentRank = getRank(currentPkg.priority)
            val minRank = getRank(minPkg.priority)
            if (currentRank < minRank) {
                minimumPackagePriorityIndex = j
            }
            else if (currentRank == minRank && currentPkg.weight > minPkg.weight) {
                minimumPackagePriorityIndex = j
            }
        }
        swapPackages(minimumPackagePriorityIndex, i, sortedList)
    }

    return sortedList
}

private fun swapPackages(
    minimumPackagePriorityIndex: Int,
    i: Int,
    sortedList: MutableList<LogisticsPackage>
) {
    if (minimumPackagePriorityIndex != i) {
        val temp = sortedList[i]
        sortedList[i] = sortedList[minimumPackagePriorityIndex]
        sortedList[minimumPackagePriorityIndex] = temp
    }
}