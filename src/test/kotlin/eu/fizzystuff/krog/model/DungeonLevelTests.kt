package eu.fizzystuff.krog.model

import com.googlecode.lanterna.TextCharacter
import org.junit.Assert
import org.junit.Test

class DungeonLevelTests {

    @Test
    fun testSetAndGetTile() {
        val level = DungeonLevel(80, 20)
        val tile = Tile(WorldPrintableEntity(TextCharacter.DEFAULT_CHARACTER), false, false)

        level.setTileAt(55, 17, tile)

        Assert.assertEquals(tile, level.getTileAt(55, 17))
    }
}