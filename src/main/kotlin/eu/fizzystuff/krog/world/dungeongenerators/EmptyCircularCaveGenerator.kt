package eu.fizzystuff.krog.world.dungeongenerators

import com.google.inject.Inject
import eu.fizzystuff.krog.world.DungeonLevel
import eu.fizzystuff.krog.world.Tile
import org.apache.commons.math3.ml.distance.EuclideanDistance
import org.apache.commons.math3.random.RandomGenerator

class EmptyCircularCaveGenerator @Inject constructor(random: RandomGenerator) : DungeonLevelGenerator {

    val random = random

    override fun generate(width: Int, height: Int): DungeonLevel {
        val level = DungeonLevel(width, height)

        val radius = random.nextInt(Math.min(level.width, level.height) / 2 - 4) + 4
        val centerx = level.width / 2;
        val centery = level.height / 2

        for (i in 0..level.width - 1) {
            for (j in 0..level.height - 1) {
                if (EuclideanDistance().compute(
                        doubleArrayOf(centerx.toDouble(), centery.toDouble()),
                        doubleArrayOf(i.toDouble(), j.toDouble())) < radius) {
                    level.setTileAt(i, j, Tile.floorTile)
                } else {
                    level.setTileAt(i, j, Tile.wallTile)
                }
            }
        }

        return level
    }
}