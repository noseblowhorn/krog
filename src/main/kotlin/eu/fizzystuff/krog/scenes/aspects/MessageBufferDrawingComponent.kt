package eu.fizzystuff.krog.scenes.aspects

import com.google.inject.Inject
import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.screen.AbstractScreen

class MessageBufferDrawingComponent public @Inject constructor(val screen: AbstractScreen) : DrawingComponent {
    override fun draw(x: Int, y: Int) {
        throw UnsupportedOperationException()
    }

    fun draw(x: Int, y: Int, text: List<String>) {
        for (i in 0..79) {
            for (j in 0..1) {
                screen.setCharacter(i, j, TextCharacter(' '))
            }
        }

        for (i in 0..text.size - 1) {
            for (j in 0..text.get(i).length - 1) {
                screen.setCharacter(j, i, TextCharacter(text.get(i).elementAt(j)))
            }
        }
    }
}