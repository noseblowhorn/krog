package eu.fizzystuff.krog.model

abstract class Actor(entity: WorldPrintableEntity, initialx: Int, initialy: Int) {
    var printableEntity: WorldPrintableEntity

    var x: Int
    var y: Int

    var actionPoints: Int
    var actionCost: Int
    var speed: Int

    var ai: (actor: Actor) -> Unit

    init {
        printableEntity = entity
        x = initialx
        y = initialy
        actionPoints = 0
        actionCost = 0
        speed = 100
        ai = { x -> ; }
    }

    fun processAi() {
        ai(this)
    }
}