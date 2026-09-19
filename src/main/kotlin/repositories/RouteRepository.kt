package org.izaki.repositories

import org.izaki.domain.model.Route

interface RouteRepository {
    fun getAll(): List<Route>
}