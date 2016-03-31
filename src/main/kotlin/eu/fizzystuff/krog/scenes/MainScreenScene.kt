package eu.fizzystuff.krog.scenes

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
        MainMapWindow().draw(screen, 0, 1)
    }

    override fun acceptInput(input: KeyStroke) {

        inputNodes.map { x -> x.process(input) }

        calculateVisibility()
    }

    override fun tick() {
        while (true) {
            logicNodes.map { x -> x.process() }

            if (PlayerCharacter.instance.actionPoints >= PlayerCharacter.instance.actionCost) {
                break
            }
        }
    }

    private fun calculateVisibility() {
        val visibilityMap = RaycastingVisibilityStrategy().calculateVisibility(WorldState.instance.currentDungeonLevel,
                PlayerCharacter.instance.x, PlayerCharacter.instance.y, 4)

        for (x in 0..WorldState.instance.currentDungeonLevel.width - 1) {
            for (y in 0..WorldState.instance.currentDungeonLevel.height - 1) {
                WorldState.instance.currentDungeonLevel.setVisible(x, y, visibilityMap[x][y])
            }
        }
    }

}