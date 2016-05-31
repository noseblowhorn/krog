package eu.fizzystuff.krog.model.dungeongenerators

import com.google.inject.Inject
import eu.fizzystuff.krog.model.DungeonLevel
import eu.fizzystuff.krog.model.DungeonTransitionPoint
import eu.fizzystuff.krog.model.Tile
import eu.fizzystuff.krog.model.dungeongenerators.objects.DefaultObjectGenerator
import org.apache.commons.math3.random.RandomGenerator

class RandomWalkCaveGenerator @Inject constructor(random: RandomGenerator) : DungeonLevelGenerator {

    val random = random

    override fun generate(width: Int, height: Int): DungeonLevel {
        val level = DungeonLevel(width, height)
        level.fillWithTile(Tile.wallTile)

        // Unbounded worst case performance is certainly fun, but we want to be sure this terminates.
        // Wait, why am I writing the bogosort of dungeon generation again?
        val maximumWalkSteps = width * height

        // Initial position.
        var x = random.nextInt(width - 2) + 1
        var y = random.nextInt(height - 2) + 1

        level.transitionPoints.add(DungeonTransitionPoint(x, y, null))

        for (i in 1..maximumWalkSteps) {
            level.setTileAt(x, y, Tile.floorTile)

            x += getNextStepX(x, width)
            y += getNextStepY(y, height)
        }

        DefaultObjectGenerator(random).generate(level)

        return level
    }

    private fun getNextStepX(x: Int, width: Int): Int {
        var dx = random.nextDouble() * 2.0 - 1.0

        // we want this to be biased so edges of the map "repel" the walk
        if (x < 5) {
            dx += 0.2 * (5.0 - x)
        } else if (x > width - 5) {
            dx -= 0.2 * (width - x)
        }

        return if (dx <= -0.33) -1 else if (dx >= 0.33) 1 else 0
    }

    private fun getNextStepY(y: Int, height: Int): Int {
        var dy = random.nextDouble() * 2.0 - 1.0

        // we want this to be biased so edges of the map "repel" the walk
        if (y < 3) {
            dy += 0.34 * (3.0 - y)
        } else if (y > height - 3) {
            dy -= 0.34 * (height - y)
        }

        return if (dy <= -0.33) -1 else if (dy >= 0.33) 1 else 0
    }
}