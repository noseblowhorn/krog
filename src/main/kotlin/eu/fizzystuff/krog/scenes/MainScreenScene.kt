package eu.fizzystuff.krog.scenes

import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.screen.AbstractScreen
import eu.fizzystuff.krog.scenes.mainscreen.AddActionPoints
import eu.fizzystuff.krog.scenes.mainscreen.NpcAI
import eu.fizzystuff.krog.scenes.mainscreen.input.ExitGame
import eu.fizzystuff.krog.scenes.mainscreen.input.PlayerCharacterMovement
import eu.fizzystuff.krog.scenes.visibility.RaycastingVisibilityStrategy
import eu.fizzystuff.krog.world.PlayerCharacter
import eu.fizzystuff.krog.world.WorldState

class MainScreenScene : Scene {
    val inputNodes: List<InputNode> = listOf(PlayerCharacterMovement(), ExitGame())
    val logicNodes: List<LogicNode> = listOf(AddActionPoints(), NpcAI())

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

        inputNodes.map { x -> x.process(input) }

        calculateVisibility()
    }

    override fun tick() {
        while (true) {
            logicNodes.map { x -> x.process() }

            if (PlayerCharacter.instance.actor.actionPoints >= PlayerCharacter.instance.actor.actionCost) {
                break
            }
        }
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