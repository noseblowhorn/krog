package eu.fizzystuff.krog.scenes.aspects

import com.googlecode.lanterna.screen.AbstractScreen

interface DrawingComponent {
    fun draw(x: Int = 0, y: Int = 0)
}