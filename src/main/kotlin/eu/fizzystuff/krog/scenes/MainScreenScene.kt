package eu.fizzystuff.krog.scenes

import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.screen.AbstractScreen
import eu.fizzystuff.krog.scenes.mainscreen.ExitGame
import eu.fizzystuff.krog.scenes.mainscreen.PlayerCharacterMovement
import eu.fizzystuff.krog.scenes.visibility.RaycastingVisibilityStrategy
import eu.fizzystuff.krog.world.PlayerCharacter
import eu.fizzystuff.krog.world.WorldState

class MainScreenScene : Scene {
    val logicNodes: List<LogicNode> = listOf(PlayerCharacterMovement(), ExitGame())

    override fun init() {
        calculateVisibility()
    }

    override fun draw(screen: AbstractScreen) {
        val dungeonLevel = WorldState.currentDungeonLevel

        for (i in 0..dungeonLevel.width - 1) {
            for (j in 0..dungeonLevel.height - 1) {
                val actor = dungeonLevel.getActorAt(i, j)
                if (actor != null) {
                    screen.setCharacter(i, j, actor.printableEntity.character)
                } else {
                    if (dungeonLevel.seen[i][j]) {
                        screen.setCharacter(i, j, dungeonLevel.getPrintableEntityAt(i, j).character)
                    } else {
                        screen.setCharacter(i, j, TextCharacter(' ', TextColor.ANSI.BLACK, TextColor.ANSI.BLACK))
                    }
                }
            }
        }

        screen.refresh()
    }

    override fun acceptInput(input: KeyStroke) {

        logicNodes.map { x -> x.process(input) }

        calculateVisibility()
    }

    private fun calculateVisibility() {
        val visibilityMap = RaycastingVisibilityStrategy().calculateVisibility(WorldState.currentDungeonLevel,
                PlayerCharacter.instance.actor.x, PlayerCharacter.instance.actor.y, 4)

        for (x in 0..WorldState.currentDungeonLevel.width - 1) {
            for (y in 0..WorldState.currentDungeonLevel.height - 1) {
                WorldState.currentDungeonLevel.setVisible(x, y, visibilityMap[x][y])
            }
        }
    }

}