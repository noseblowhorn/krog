package eu.fizzystuff.krog.world

class Actor(entity: WorldPrintableEntity, initialx: Int, initialy: Int) {
    public var printableEntity: WorldPrintableEntity

    public var x: Int
    public var y: Int

    public var actionPoints: Int
    public var actionCost: Int
    public var speed: Int

    init {
        printableEntity = entity
        x = initialx
        y = initialy
        actionPoints = 0
        actionCost = 0
        speed = 100
    }
}