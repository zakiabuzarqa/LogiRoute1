package org.izaki
import org.izaki.logiroute.decorator.ColdChainDecorator
import org.izaki.logiroute.decorator.ExpressInsuranceDecorator
import org.izaki.logiroute.decorator.FragileHandlingDecorator
import org.izaki.logiroute.decorator.PackageComponent
import org.izaki.logiroute.router.DijkstraRouter
import org.izaki.logiroute.router.LeastHopRouter
import org.izaki.logiroute.router.WarehouseGraph
import org.izaki.logiroute.router.WeightedWarehouseGraph
import org.izaki.logiroute.decorator.*
fun main() {
    val unweightedGraph = WarehouseGraph().apply {
        addConnection("WH-A", "WH-B")
        addConnection("WH-B", "WH-C")
        addConnection("WH-A", "WH-D")
        addConnection("WH-D", "WH-C")
    }

    val weightedGraph = WeightedWarehouseGraph().apply {
        addConnection("WH-A", "WH-B", 100.0)
        addConnection("WH-B", "WH-C", 150.0)
        addConnection("WH-A", "WH-D", 400.0)
        addConnection("WH-D", "WH-C", 50.0)
    }

    val leastHopRouter = LeastHopRouter(unweightedGraph)
    val dijkstraRouter = DijkstraRouter(weightedGraph)

    println("BFS Path: ${leastHopRouter.findRoute("WH-A", "WH-C")}")
    println("Dijkstra Path: ${dijkstraRouter.findShortestPath("WH-A", "WH-C")}")

    val basePackage: PackageComponent = ConcretePackage("PKG-101", 4.5, 50.0)
    val finalPackage: PackageComponent = ExpressInsuranceDecorator(
        ColdChainDecorator(
            FragileHandlingDecorator(basePackage, 15.0),
            1.20
        ),
        25.0
    )
    println("Rate: $${finalPackage.calculateRate()}")
}