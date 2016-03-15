package eu.fizzystuff.krog.world

import com.google.common.base.Preconditions
import com.googlecode.lanterna.TextCharacter

class DungeonLevel(width: Int, height: Int) {
    private val width: Int
    private val height: Int

    private val tiles: Array<Array<Tile>>

    init {
        this.width = width
        this.height = height

        val dummyEntity = WorldPrintableEntity(TextCharacter.DEFAULT_CHARACTER)

        tiles = Array(width, {i -> Array(height, {j -> floorTile}) })
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
}