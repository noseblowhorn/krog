package eu.fizzystuff.krog.model.dungeongenerators.objects

import eu.fizzystuff.krog.model.DungeonLevel

interface ObjectGenerator {
    fun generate(dungeonLevel: DungeonLevel)
}