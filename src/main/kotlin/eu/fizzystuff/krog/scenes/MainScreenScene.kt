package eu.fizzystuff.krog.scenes

import com.google.inject.Inject
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.screen.AbstractScreen
import com.googlecode.lanterna.terminal.Terminal
import eu.fizzystuff.krog.scenes.mainscreen.AddActionPoints
import eu.fizzystuff.krog.scenes.mainscreen.NpcAI
import eu.fizzystuff.krog.scenes.aspects.MainMapDrawingAspect
import eu.fizzystuff.krog.scenes.mainscreen.input.ExitGame
import eu.fizzystuff.krog.scenes.mainscreen.input.PlayerCharacterMovement
import eu.fizzystuff.krog.scenes.visibility.RaycastingVisibilityStrategy
import eu.fizzystuff.krog.model.PlayerCharacter
import eu.fizzystuff.krog.model.WorldState

class MainScreenScene public @Inject constructor(val screen: AbstractScreen,
                                                 val terminal: Terminal) : Scene() {

    override fun run(): SceneTransition? {
        while(true) {
            draw(screen)
            acceptInput(terminal.readInput())
            tick()
        }
    }

    override fun destroy() {
        throw UnsupportedOperationException()
    }

    val inputNodes: List<InputNode> = listOf(PlayerCharacterMovement(), ExitGame())
    val logicNodes: List<LogicNode> = listOf(AddActionPoints(), NpcAI())

    override fun init() {
        calculateVisibility()
    }

    fun draw(screen: AbstractScreen) {
        MainMapDrawingAspect().draw(screen, 0, 1)
    }

    fun acceptInput(input: KeyStroke): SceneTransition? {

        inputNodes.map { x -> x.process(input) }

        calculateVisibility()

        return null
    }

    fun tick() {
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