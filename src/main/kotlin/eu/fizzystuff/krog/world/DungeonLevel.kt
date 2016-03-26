package eu.fizzystuff.krog.world

import com.google.common.base.Preconditions
import com.googlecode.lanterna.TextCharacter

class DungeonLevel(width: Int, height: Int) {
    public val width: Int
    public val height: Int

    private val tiles: Array<Array<Tile>>
    val seen: Array<Array<Boolean>>
    val visible: Array<Array<Boolean>>
    val actors: MutableList<Actor>
    val npcs: MutableList<Npc>

    val transitionPoints: MutableList<DungeonTransitionPoint> = arrayListOf()

    init {
        this.width = width
        this.height = height

        tiles = Array(width, {i -> Array(height, {j -> Tile.Tiles.floorTile}) })
        seen = Array(width, {i -> Array(height, {j -> false}) })
        visible = Array(width, {i -> Array(height, {j -> false}) })
        actors = mutableListOf()
        npcs = mutableListOf()
    }

    public fun getTileAt(x: Int, y: Int): Tile {
        Preconditions.checkState(x >= 0)
        Preconditions.checkState(y >= 0)
        Preconditions.checkState(x < width)
        Preconditions.checkState(y < height)

        return tiles[x][y]
    }

    public fun setTileAt(x: Int, y: Int, tile: Tile) {
        Preconditions.checkState(x >= 0)
        Preconditions.checkState(y >= 0)
        Preconditions.checkState(x < width)
        Preconditions.checkState(y < height)

        tiles[x][y] = tile
    }

    public fun getPrintableEntityAt(x: Int, y: Int): WorldPrintableEntity {
        return tiles[x][y].printableEntity
    }

    public fun setVisible(x: Int, y: Int, isVisible: Boolean) {
        visible[x][y] = isVisible
        if (isVisible) {
            seen[x][y] = isVisible
        }
    }

    public fun addActor(actor: Actor) {
        actors.add(actor)
    }

    public fun getActorAt(x: Int, y: Int): Actor? {
        return actors.filter { actor -> actor.x == x && actor.y == y }.firstOrNull()
    }

    public fun fillWithTile(tile: Tile) {
        for (x in 0..width - 1) {
            for (y in 0..height - 1) {
                setTileAt(x, y, tile)
            }
        }
    }

    companion object DungeonLevels {

    }
}