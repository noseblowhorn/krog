package eu.fizzystuff.krog.scenes.mainscreen.input

import com.google.common.collect.ImmutableSet
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import eu.fizzystuff.krog.scenes.InputNode
import eu.fizzystuff.krog.scenes.LogicNode
import eu.fizzystuff.krog.world.PlayerCharacter
import eu.fizzystuff.krog.world.WorldState

class PlayerCharacterMovement : InputNode {

    private val MOVE_AP_COST = 400

    val NORTH_MOVE_KEYS = ImmutableSet.of(KeyStroke('w', false, false),
            KeyStroke(KeyType.ArrowUp),
            KeyStroke('8', false, false))
    val SOUTH_MOVE_KEYS = ImmutableSet.of(KeyStroke('s', false, false),
            KeyStroke(KeyType.ArrowDown),
            KeyStroke('2', false, false))
    val WEST_MOVE_KEYS = ImmutableSet.of(KeyStroke('a', false, false),
            KeyStroke(KeyType.ArrowLeft),
            KeyStroke('4', false, false))
    val EAST_MOVE_KEYS = ImmutableSet.of(KeyStroke('d', false, false),
            KeyStroke(KeyType.ArrowRight),
            KeyStroke('6', false, false))
    val NORTHWEST_MOVE_KEYS = ImmutableSet.of(KeyStroke('7', false, false))
    val NORTHEAST_MOVE_KEYS = ImmutableSet.of(KeyStroke('9', false, false))
    val SOUTHWEST_MOVE_KEYS = ImmutableSet.of(KeyStroke('1', false, false))
    val SOUTHEAST_MOVE_KEYS = ImmutableSet.of(KeyStroke('3', false, false))


    override fun process(keyStroke: KeyStroke) {
        val pc = PlayerCharacter.instance
        
        val x = pc.x
        val y = pc.y

        if (keyStroke in NORTH_MOVE_KEYS && canMoveTo(x, y - 1)) {
            pc.y -= 1
        } else if (keyStroke in SOUTH_MOVE_KEYS && canMoveTo(x, y + 1)) {
            pc.y += 1
        } else if (keyStroke in WEST_MOVE_KEYS && canMoveTo(x - 1, y)) {
            pc.x -= 1
        } else if (keyStroke in EAST_MOVE_KEYS && canMoveTo(x + 1, y)) {
            pc.x += 1
        } else if (keyStroke in SOUTHWEST_MOVE_KEYS && canMoveTo(x - 1, y + 1)) {
            pc.x -= 1
            pc.y += 1
        } else if (keyStroke in SOUTHEAST_MOVE_KEYS && canMoveTo(x + 1, y + 1)) {
            pc.x += 1
            pc.y += 1
        } else if (keyStroke in NORTHWEST_MOVE_KEYS && canMoveTo(x - 1, y - 1)) {
            pc.x -= 1
            pc.y -= 1
        } else if (keyStroke in NORTHEAST_MOVE_KEYS && canMoveTo(x + 1, y - 1)) {
            pc.x += 1
            pc.y -= 1
        } else {
            return
        }

        pc.actionCost = MOVE_AP_COST
        pc.actionPoints -= MOVE_AP_COST
    }

    private fun canMoveTo(x: Int, y: Int): Boolean {
        val dungeonLevel = WorldState.instance.currentDungeonLevel

        if (x < 0 || y < 0 || x >= dungeonLevel.width || y >= dungeonLevel.height) {
            return false
        }

        if (dungeonLevel.actors.any { actor -> actor.x == x && actor.y == y }) {
            return false
        }

        val tile = dungeonLevel.getTileAt(x, y)

        return tile.traversable
    }
}