package eu.fizzystuff.krog.scenes.visibility

import com.google.common.collect.ImmutableSet
import eu.fizzystuff.krog.world.DungeonLevel
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D

/**
 * Hey, let's be sloppy and inefficient! Here, we can calculate a path between the origin point and each and every
 * tile at the edges of the level. And see if something sticks between.
 */
class RaycastingVisibilityStrategy : VisibilityStrategy {
    override fun calculateVisibility(dungeonLevel: DungeonLevel, originX: Int, originY: Int, maxDistance: Int) {
        flagDungeonAsNotVisible(dungeonLevel)
        dungeonLevel.seen[originX][originY] = true
        dungeonLevel.visible[originX][originY] = true
        val edgePointSet = buildEdgePointSet(dungeonLevel)

        for (edgePoint in edgePointSet) {
            var radius = 0.0;
            val angle = Math.atan2((edgePoint.second - originY).toDouble(), (edgePoint.first - originX).toDouble())

            do {
                radius += 1.0

                val x = (radius * Math.cos(angle)).toInt() + originX
                val y = (radius * Math.sin(angle)).toInt() + originY

                if (!pointWithinBounds(dungeonLevel, x, y)) {
                    break
                }

                val tile = dungeonLevel.getTileAt(x, y)
                if (tile.translucent) {
                    dungeonLevel.seen[x][y] = true
                    dungeonLevel.visible[x][y] = true
                } else {
                    // mark this tile as seen and bail - we can't see anything beyond it
                    dungeonLevel.seen[x][y] = true
                    dungeonLevel.visible[x][y] = true
                    break
                }

            } while (radius.toInt() <= maxDistance)
        }
    }

    private fun pointWithinBounds(dungeonLevel: DungeonLevel, x: Int, y: Int): Boolean {
        return x >= 0 && y >= 0 && x < dungeonLevel.width && y < dungeonLevel.height
    }

    private fun flagDungeonAsNotVisible(dungeonLevel: DungeonLevel) {
        for (i in 0..dungeonLevel.width - 1) {
            for (j in 0..dungeonLevel.height - 1) {
                dungeonLevel.visible[i][j] = false
            }
        }
    }

    private fun buildEdgePointSet(dungeonLevel: DungeonLevel): ImmutableSet<Pair<Int, Int>> {
        val edgePointSetBuilder = ImmutableSet.builder<Pair<Int, Int>>();

        for (i in 0..dungeonLevel.width - 1) {
            edgePointSetBuilder.add(Pair(i, 0))
        }
        for (j in 0..dungeonLevel.height - 1) {
            edgePointSetBuilder.add(Pair(0, j))
        }
        for (i in 0..dungeonLevel.width - 1) {
            edgePointSetBuilder.add(Pair(i, dungeonLevel.height - 1))
        }
        for (j in 0..dungeonLevel.height - 1) {
            edgePointSetBuilder.add(Pair(dungeonLevel.width - 1, j))
        }

        return edgePointSetBuilder.build()
    }
}