package eu.fizzystuff.krog.scenes

import com.google.common.collect.ImmutableList
import com.google.inject.Inject
import com.googlecode.lanterna.terminal.Terminal
import eu.fizzystuff.krog.scenes.aspects.MainMapDrawingComponent
import eu.fizzystuff.krog.scenes.aspects.MessageBufferDrawingComponent
import eu.fizzystuff.krog.ui.MessageBuffer

class MainScreenMessageBufferScene public @Inject constructor(val mainMapDrawingComponent: MainMapDrawingComponent,
                                                              val messageBufferDrawingComponent: MessageBufferDrawingComponent,
                                                              val terminal: Terminal,
                                                              val messageBuffer: MessageBuffer) : Scene() {

    val CHARS_PER_LINE = 80

    override fun init() {

    }

    override fun destroy() {

    }

    override fun run(): SceneTransition? {
        while (messageBuffer.size() > 0) {
            mainMapDrawingComponent.draw(0, 2)
            messageBufferDrawingComponent.draw(0, 0, ImmutableList.of(
                    messageBuffer.poll(),
                    messageBuffer.poll()
            ))
            terminal.readInput()
        }

        return SceneTransition(MainScreenScene::class.java)
    }
}