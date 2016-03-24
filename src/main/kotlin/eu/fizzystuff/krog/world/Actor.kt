package eu.fizzystuff.krog.world

class Actor(entity: WorldPrintableEntity, initialx: Int, initialy: Int) {
    public var printableEntity: WorldPrintableEntity

    public var x: Int
    public var y: Int

    init {
        printableEntity = entity
        x = initialx
        y = initialy
    }
}