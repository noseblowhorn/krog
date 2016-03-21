package eu.fizzystuff.krog.world.dungeongenerators

import eu.fizzystuff.krog.world.DungeonLevel

interface DungeonLevelGenerator {
    fun generate(width: Int, height: Int): DungeonLevel
}