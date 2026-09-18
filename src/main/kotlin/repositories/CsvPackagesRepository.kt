package org.izaki.repositories

import org.izaki.domain.model.LogisticsPackage

class CsvPackagesRepository(
    private val packagesFilePath: String,
    private val warehouseFilePath: String
): PackageRepository {

    override fun getAll(): List<LogisticsPackage> {

    }
}