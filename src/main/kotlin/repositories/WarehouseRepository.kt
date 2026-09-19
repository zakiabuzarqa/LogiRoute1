package org.izaki.repositories

import org.izaki.domain.model.Route
import org.izaki.domain.model.Warehouse

interface WarehouseRepository {
    fun getAll(): List<Warehouse>
}