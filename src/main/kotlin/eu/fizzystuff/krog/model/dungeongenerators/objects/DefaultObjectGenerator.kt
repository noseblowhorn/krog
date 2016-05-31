package eu.fizzystuff.krog.model.dungeongenerators.objects

import com.google.inject.Inject
import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.TextColor
import eu.fizzystuff.krog.model.DungeonLevel
import eu.fizzystuff.krog.model.Npc
import eu.fizzystuff.krog.model.WorldPrintableEntity
import org.apache.commons.math3.random.RandomGenerator

class DefaultObjectGenerator @Inject constructor(random: RandomGenerator): ObjectGenerator {

    val random = random

    override fun generate(dungeonLevel: DungeonLevel) {
        val validPlacements = getValidPlacementSet(dungeonLevel)

        val npcCount = random.nextInt(validPlacements.size / 12)

        for (i in 1..npcCount) {
            val index = random.nextInt(validPlacements.size)
            val location = validPlacements.get(index)

            dungeonLevel.addActor(generateRandomNpc(location.first, location.second))

            validPlacements.removeAt(index)
        }
    }

    private fun generateRandomNpc(x: Int, y: Int): Npc {
        return Npc("shitling", WorldPrintableEntity(TextCharacter('s', TextColor.ANSI.RED, TextColor.ANSI.BLACK)), x, y)
    }

    private fun getValidPlacementSet(dungeonLevel: DungeonLevel): MutableList<Pair<Int, Int>> {
        val validPlacements: MutableList<Pair<Int, Int>> = mutableListOf()

        for (x in 0..dungeonLevel.width - 1) {
            for (y in 0..dungeonLevel.height - 1) {
                if (dungeonLevel.getTileAt(x, y).traversable) {
                    validPlacements.add(Pair(x, y))
                }
            }
        }

        return validPlacements
    }
}