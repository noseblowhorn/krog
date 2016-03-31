package eu.fizzystuff.krog.world

class WorldState {

    var currentDungeonLevel: DungeonLevel

    init {
        currentDungeonLevel = DungeonLevel(0, 0)
    }

    companion object WorldStateHolder {
        var instance = WorldState()
    }
}