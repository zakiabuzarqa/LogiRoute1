package org.izaki.repositories

import org.izaki.domain.model.LogisticsPackage

interface PackageRepository {

    fun getAll() : List<LogisticsPackage>

}