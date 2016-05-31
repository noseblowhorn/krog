package eu.fizzystuff.krog.model.dungeongenerators

import eu.fizzystuff.krog.model.DungeonLevel

interface DungeonLevelGenerator {
    fun generate(width: Int, height: Int): DungeonLevel
}