package eu.fizzystuff.krog.model

class WorldState {

    var currentDungeonLevel: DungeonLevel

    init {
        currentDungeonLevel = DungeonLevel(0, 0)
    }

    companion object WorldStateHolder {
        var instance = WorldState()
    }
}