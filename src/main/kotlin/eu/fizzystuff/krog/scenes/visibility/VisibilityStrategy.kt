package eu.fizzystuff.krog.scenes.visibility

import eu.fizzystuff.krog.world.DungeonLevel

interface VisibilityStrategy {
    fun calculateVisibility(dungeonLevel: DungeonLevel, originX: Int, originY: Int, maxDistance: Int): Array<Array<Boolean>>
}