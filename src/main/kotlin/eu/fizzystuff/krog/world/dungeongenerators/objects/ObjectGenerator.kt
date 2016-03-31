package eu.fizzystuff.krog.world.dungeongenerators.objects

import eu.fizzystuff.krog.world.DungeonLevel

interface ObjectGenerator {
    fun generate(dungeonLevel: DungeonLevel)
}