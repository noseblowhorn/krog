package eu.fizzystuff.krog.scenes.mainscreen

import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import eu.fizzystuff.krog.scenes.mainscreen.input.PlayerCharacterMovement
import eu.fizzystuff.krog.model.DungeonLevel
import eu.fizzystuff.krog.model.PlayerCharacter
import eu.fizzystuff.krog.model.Tile
import eu.fizzystuff.krog.model.WorldState
import eu.fizzystuff.krog.ui.MessageBuffer
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PlayerCharacterMovementTests {


    val messageBuffer: MessageBuffer = MessageBuffer()

    @Before
    fun setUp() {
        WorldState.instance.currentDungeonLevel = DungeonLevel(3, 3)
        PlayerCharacter.instance = PlayerCharacter()
        PlayerCharacter.instance.x = 1
        PlayerCharacter.instance.y = 1
    }

    @Test
    fun testMoveNorth() {
        PlayerCharacterMovement(messageBuffer).process(KeyStroke(KeyType.ArrowUp))

        Assert.assertEquals(0, PlayerCharacter.instance.y)
        Assert.assertEquals(1, PlayerCharacter.instance.x)

        PlayerCharacterMovement(messageBuffer).process(KeyStroke(KeyType.ArrowUp))

        Assert.assertEquals(0, PlayerCharacter.instance.y)
        Assert.assertEquals(1, PlayerCharacter.instance.x)
    }

    @Test
    fun testMoveSouth() {
        PlayerCharacterMovement(messageBuffer).process(KeyStroke(KeyType.ArrowDown))

        Assert.assertEquals(2, PlayerCharacter.instance.y)
        Assert.assertEquals(1, PlayerCharacter.instance.x)

        PlayerCharacterMovement(messageBuffer).process(KeyStroke(KeyType.ArrowDown))

        Assert.assertEquals(2, PlayerCharacter.instance.y)
        Assert.assertEquals(1, PlayerCharacter.instance.x)
    }

    @Test
    fun testMoveWest() {
        PlayerCharacterMovement(messageBuffer).process(KeyStroke(KeyType.ArrowLeft))

        Assert.assertEquals(1, PlayerCharacter.instance.y)
        Assert.assertEquals(0, PlayerCharacter.instance.x)

        PlayerCharacterMovement(messageBuffer).process(KeyStroke(KeyType.ArrowLeft))

        Assert.assertEquals(1, PlayerCharacter.instance.y)
        Assert.assertEquals(0, PlayerCharacter.instance.x)
    }

    @Test
    fun testMoveEast() {
        PlayerCharacterMovement(messageBuffer).process(KeyStroke(KeyType.ArrowRight))

        Assert.assertEquals(1, PlayerCharacter.instance.y)
        Assert.assertEquals(2, PlayerCharacter.instance.x)

        PlayerCharacterMovement(messageBuffer).process(KeyStroke(KeyType.ArrowRight))

        Assert.assertEquals(1, PlayerCharacter.instance.y)
        Assert.assertEquals(2, PlayerCharacter.instance.x)
    }

    @Test
    fun testMoveNorthwest() {
        PlayerCharacterMovement(messageBuffer).process(KeyStroke('7', false, false))

        Assert.assertEquals(0, PlayerCharacter.instance.y)
        Assert.assertEquals(0, PlayerCharacter.instance.x)

        PlayerCharacterMovement(messageBuffer).process(KeyStroke('7', false, false))

        Assert.assertEquals(0, PlayerCharacter.instance.y)
        Assert.assertEquals(0, PlayerCharacter.instance.x)
    }

    @Test
    fun testMoveNortheast() {
        PlayerCharacterMovement(messageBuffer).process(KeyStroke('9', false, false))

        Assert.assertEquals(0, PlayerCharacter.instance.y)
        Assert.assertEquals(2, PlayerCharacter.instance.x)

        PlayerCharacterMovement(messageBuffer).process(KeyStroke('9', false, false))

        Assert.assertEquals(0, PlayerCharacter.instance.y)
        Assert.assertEquals(2, PlayerCharacter.instance.x)
    }

    @Test
    fun testMoveSouthwest() {
        PlayerCharacterMovement(messageBuffer).process(KeyStroke('1', false, false))

        Assert.assertEquals(2, PlayerCharacter.instance.y)
        Assert.assertEquals(0, PlayerCharacter.instance.x)

        PlayerCharacterMovement(messageBuffer).process(KeyStroke('1', false, false))

        Assert.assertEquals(2, PlayerCharacter.instance.y)
        Assert.assertEquals(0, PlayerCharacter.instance.x)
    }

    @Test
    fun testMoveSoutheast() {
        PlayerCharacterMovement(messageBuffer).process(KeyStroke('3', false, false))

        Assert.assertEquals(2, PlayerCharacter.instance.y)
        Assert.assertEquals(2, PlayerCharacter.instance.x)

        PlayerCharacterMovement(messageBuffer).process(KeyStroke('3', false, false))

        Assert.assertEquals(2, PlayerCharacter.instance.y)
        Assert.assertEquals(2, PlayerCharacter.instance.x)
    }

    @Test
    fun testMoveThroughObstruction() {
        WorldState.instance.currentDungeonLevel.setTileAt(1, 0, Tile.wallTile)

        PlayerCharacterMovement(messageBuffer).process(KeyStroke(KeyType.ArrowUp))

        Assert.assertEquals(1, PlayerCharacter.instance.y)
        Assert.assertEquals(1, PlayerCharacter.instance.x)
    }
}