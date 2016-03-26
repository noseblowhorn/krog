package eu.fizzystuff.krog.scenes.visibility

import eu.fizzystuff.krog.world.DungeonLevel

/**
 * Hey, let's be sloppy and inefficient! Here, we can calculate a path between the origin point and each and every
 * tile at the edges of the level. And see if something sticks between.
 */
class RaycastingVisibilityStrategy : VisibilityStrategy {
    override fun calculateVisibility(dungeonLevel: DungeonLevel, originX: Int, originY: Int, maxDistance: Int): Array<Array<Boolean>> {
        val edgePointSet = buildEdgePointSet(dungeonLevel)
        val visible = Array(dungeonLevel.width, {i -> Array(dungeonLevel.height, {j -> false}) })
        visible[originX][originY] = true

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
                    visible[x][y] = true
                } else {
                    // mark this tile as seen and bail - we can't see anything beyond it
                    visible[x][y] = true
                    break
                }

            } while (radius.toInt() <= maxDistance)
        }

        return visible
    }

    private fun pointWithinBounds(dungeonLevel: DungeonLevel, x: Int, y: Int): Boolean {
        return x >= 0 && y >= 0 && x < dungeonLevel.width && y < dungeonLevel.height
    }

    private fun buildEdgePointSet(dungeonLevel: DungeonLevel): Set<Pair<Int, Int>> =
        setOf((0..dungeonLevel.width - 1).map({x -> Pair(x, 0)}),
            (0..dungeonLevel.width - 1).map({x -> Pair(x, dungeonLevel.height - 1)}),
            (0..dungeonLevel.height - 1).map({x -> Pair(0, x)}),
            (0..dungeonLevel.height - 1).map({x -> Pair(dungeonLevel.width - 1, x)}))
            .flatten().toSet()
}