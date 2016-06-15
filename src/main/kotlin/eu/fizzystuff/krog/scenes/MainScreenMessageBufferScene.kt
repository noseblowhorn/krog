package eu.fizzystuff.krog.scenes

import com.google.common.collect.ImmutableList
import com.google.inject.Inject
import com.googlecode.lanterna.screen.AbstractScreen
import com.googlecode.lanterna.terminal.Terminal
import eu.fizzystuff.krog.scenes.aspects.MainMapDrawingComponent
import eu.fizzystuff.krog.scenes.aspects.MessageBufferDrawingComponent
import eu.fizzystuff.krog.ui.MessageBuffer

class MainScreenMessageBufferScene public @Inject constructor(val mainMapDrawingComponent: MainMapDrawingComponent,
                                                              val messageBufferDrawingComponent: MessageBufferDrawingComponent,
                                                              val terminal: Terminal,
                                                              val messageBuffer: MessageBuffer,
                                                              val screen: AbstractScreen) : Scene() {

    val CHARS_PER_LINE = 80

    override fun init() {

    }

    override fun destroy() {

    }

    override fun run(): SceneTransition? {
        while (messageBuffer.size() > CHARS_PER_LINE * 2) {
            mainMapDrawingComponent.draw(0, 2)
            messageBufferDrawingComponent.draw(0, 0, ImmutableList.of(
                    messageBuffer.poll(CHARS_PER_LINE),
                    messageBuffer.poll(CHARS_PER_LINE - 7) + if (messageBuffer.size() > 0) " (more)" else ""
            ))

            screen.refresh()

            terminal.readInput()
        }

        return SceneTransition(MainScreenScene::class.java)
    }
}