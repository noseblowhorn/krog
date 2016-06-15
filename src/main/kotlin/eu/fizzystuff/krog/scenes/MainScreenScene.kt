package eu.fizzystuff.krog.scenes

import com.google.common.collect.ImmutableList
import com.google.inject.Guice
import com.google.inject.Inject
import com.google.inject.Injector
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.screen.AbstractScreen
import com.googlecode.lanterna.terminal.Terminal
import eu.fizzystuff.krog.main.KrogModule
import eu.fizzystuff.krog.scenes.mainscreen.AddActionPoints
import eu.fizzystuff.krog.scenes.mainscreen.NpcAI
import eu.fizzystuff.krog.scenes.aspects.MainMapDrawingComponent
import eu.fizzystuff.krog.scenes.mainscreen.input.ExitGame
import eu.fizzystuff.krog.scenes.mainscreen.input.PlayerCharacterMovement
import eu.fizzystuff.krog.scenes.visibility.RaycastingVisibilityStrategy
import eu.fizzystuff.krog.model.PlayerCharacter
import eu.fizzystuff.krog.model.WorldState
import eu.fizzystuff.krog.scenes.aspects.MessageBufferDrawingComponent
import eu.fizzystuff.krog.ui.MessageBuffer

class MainScreenScene public @Inject constructor(val injector: Injector,
                                                 val mainMapDrawingComponent: MainMapDrawingComponent,
                                                 val messageBufferDrawingComponent: MessageBufferDrawingComponent,
                                                 val terminal: Terminal,
                                                 val messageBuffer: MessageBuffer) : Scene() {

    override fun run(): SceneTransition? {
        while(true) {
            draw()
            acceptInput(terminal.readInput())
            val sceneTransition = tick()

            if (sceneTransition != null) {
                return sceneTransition
            }
        }
    }

    override fun destroy() {
    }

    var inputNodes: List<InputNode> = listOf()
    var logicNodes: List<LogicNode> = listOf()

    override fun init() {
        calculateVisibility()

        inputNodes = ImmutableList.of(injector.getInstance(PlayerCharacterMovement::class.java),
                                      injector.getInstance(ExitGame::class.java))

        logicNodes = ImmutableList.of(injector.getInstance(AddActionPoints::class.java),
                                      injector.getInstance(NpcAI::class.java))
    }

    fun draw() {
        messageBufferDrawingComponent.draw(0, 0, ImmutableList.of(messageBuffer.poll(), messageBuffer.poll()))
        mainMapDrawingComponent.draw(0, 1)
    }

    fun acceptInput(input: KeyStroke): SceneTransition? {

        inputNodes.map { x -> x.process(input) }

        calculateVisibility()

        return null
    }

    fun tick(): SceneTransition? {
        while (true) {
            logicNodes.map { x -> x.process() }

            if (PlayerCharacter.instance.actionPoints >= PlayerCharacter.instance.actionCost) {
                break
            }
        }

        if (messageBuffer.size() > 160) {
            return SceneTransition(MainScreenMessageBufferScene::class.java)
        }

        return null
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