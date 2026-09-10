package org.izaki.quick_sort

import org.izaki.domain.model.LogisticsPackage

fun quickSortPackages(packages: List<LogisticsPackage>): List<LogisticsPackage> {
    if (packages.size <= 1) return packages

    val sortedList = packages.toMutableList()
    quickSortHelper(sortedList, 0, sortedList.size - 1)
    return sortedList
}

private fun quickSortHelper(list: MutableList<LogisticsPackage>, low: Int, high: Int) {
    if (low < high) {
        val pivotIndex = partition(list, low, high)
        quickSortHelper(list, low, pivotIndex - START_OF_THE_SMALLER_PART_LIST)
        quickSortHelper(list, pivotIndex + START_OF_THE_BIGGER_PART_LIST, high)
    }
}

private fun partition(list: MutableList<LogisticsPackage>, low: Int, high: Int): Int {
    val pivot = list[high]
    var i = low - PORTIONED_LIST_OFFSET

    for (j in low until high) {
        if (list[j].weight <= pivot.weight) {
            i++
            swapPackages(i, j, list)
        }
    }
    swapPackages(i + RIGHT_PORTIONED_LIST_OFFSET, high, list)
    return i + NEW_PIVOT_OFFSET
}


fun swapPackages(
    currentPackageIndex: Int,
    nextPackageIndex: Int,
    sortedPackages: MutableList<LogisticsPackage>
) {
    var tempPackageIndex = 0
    val currentPackage = sortedPackages[currentPackageIndex]
    val nextPackage = sortedPackages[nextPackageIndex]
    tempPackageIndex = currentPackageIndex
    sortedPackages[tempPackageIndex] = nextPackage
    sortedPackages[nextPackageIndex] = currentPackage
}

const val START_OF_THE_BIGGER_PART_LIST= 1
const val START_OF_THE_SMALLER_PART_LIST= 1
const val PORTIONED_LIST_OFFSET = 1
const val RIGHT_PORTIONED_LIST_OFFSET = 1
const val NEW_PIVOT_OFFSET = 1