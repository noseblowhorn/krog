package eu.fizzystuff.krog.scenes.mainscreen

import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import eu.fizzystuff.krog.scenes.mainscreen.input.PlayerCharacterMovement
import eu.fizzystuff.krog.world.DungeonLevel
import eu.fizzystuff.krog.world.PlayerCharacter
import eu.fizzystuff.krog.world.Tile
import eu.fizzystuff.krog.world.WorldState
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PlayerCharacterMovementTests {

    @Before
    fun setUp() {
        WorldState.currentDungeonLevel = DungeonLevel(3, 3)
        PlayerCharacter.instance = PlayerCharacter()
        PlayerCharacter.instance.x = 1
        PlayerCharacter.instance.y = 1
    }

    @Test
    fun testMoveNorth() {
        PlayerCharacterMovement().process(KeyStroke(KeyType.ArrowUp))

        Assert.assertEquals(0, PlayerCharacter.instance.y)
        Assert.assertEquals(1, PlayerCharacter.instance.x)

        PlayerCharacterMovement().process(KeyStroke(KeyType.ArrowUp))

        Assert.assertEquals(0, PlayerCharacter.instance.y)
        Assert.assertEquals(1, PlayerCharacter.instance.x)
    }

    @Test
    fun testMoveSouth() {
        PlayerCharacterMovement().process(KeyStroke(KeyType.ArrowDown))

        Assert.assertEquals(2, PlayerCharacter.instance.y)
        Assert.assertEquals(1, PlayerCharacter.instance.x)

        PlayerCharacterMovement().process(KeyStroke(KeyType.ArrowDown))

        Assert.assertEquals(2, PlayerCharacter.instance.y)
        Assert.assertEquals(1, PlayerCharacter.instance.x)
    }

    @Test
    fun testMoveWest() {
        PlayerCharacterMovement().process(KeyStroke(KeyType.ArrowLeft))

        Assert.assertEquals(1, PlayerCharacter.instance.y)
        Assert.assertEquals(0, PlayerCharacter.instance.x)

        PlayerCharacterMovement().process(KeyStroke(KeyType.ArrowLeft))

        Assert.assertEquals(1, PlayerCharacter.instance.y)
        Assert.assertEquals(0, PlayerCharacter.instance.x)
    }

    @Test
    fun testMoveEast() {
        PlayerCharacterMovement().process(KeyStroke(KeyType.ArrowRight))

        Assert.assertEquals(1, PlayerCharacter.instance.y)
        Assert.assertEquals(2, PlayerCharacter.instance.x)

        PlayerCharacterMovement().process(KeyStroke(KeyType.ArrowRight))

        Assert.assertEquals(1, PlayerCharacter.instance.y)
        Assert.assertEquals(2, PlayerCharacter.instance.x)
    }

    @Test
    fun testMoveNorthwest() {
        PlayerCharacterMovement().process(KeyStroke('7', false, false))

        Assert.assertEquals(0, PlayerCharacter.instance.y)
        Assert.assertEquals(0, PlayerCharacter.instance.x)

        PlayerCharacterMovement().process(KeyStroke('7', false, false))

        Assert.assertEquals(0, PlayerCharacter.instance.y)
        Assert.assertEquals(0, PlayerCharacter.instance.x)
    }

    @Test
    fun testMoveNortheast() {
        PlayerCharacterMovement().process(KeyStroke('9', false, false))

        Assert.assertEquals(0, PlayerCharacter.instance.y)
        Assert.assertEquals(2, PlayerCharacter.instance.x)

        PlayerCharacterMovement().process(KeyStroke('9', false, false))

        Assert.assertEquals(0, PlayerCharacter.instance.y)
        Assert.assertEquals(2, PlayerCharacter.instance.x)
    }

    @Test
    fun testMoveSouthwest() {
        PlayerCharacterMovement().process(KeyStroke('1', false, false))

        Assert.assertEquals(2, PlayerCharacter.instance.y)
        Assert.assertEquals(0, PlayerCharacter.instance.x)

        PlayerCharacterMovement().process(KeyStroke('1', false, false))

        Assert.assertEquals(2, PlayerCharacter.instance.y)
        Assert.assertEquals(0, PlayerCharacter.instance.x)
    }

    @Test
    fun testMoveSoutheast() {
        PlayerCharacterMovement().process(KeyStroke('3', false, false))

        Assert.assertEquals(2, PlayerCharacter.instance.y)
        Assert.assertEquals(2, PlayerCharacter.instance.x)

        PlayerCharacterMovement().process(KeyStroke('3', false, false))

        Assert.assertEquals(2, PlayerCharacter.instance.y)
        Assert.assertEquals(2, PlayerCharacter.instance.x)
    }

    @Test
    fun testMoveThroughObstruction() {
        WorldState.currentDungeonLevel.setTileAt(1, 0, Tile.wallTile)

        PlayerCharacterMovement().process(KeyStroke(KeyType.ArrowUp))

        Assert.assertEquals(1, PlayerCharacter.instance.y)
        Assert.assertEquals(1, PlayerCharacter.instance.x)
    }
}