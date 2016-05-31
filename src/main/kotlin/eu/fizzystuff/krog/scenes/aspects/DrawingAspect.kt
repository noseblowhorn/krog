package eu.fizzystuff.krog.scenes.aspects

import com.googlecode.lanterna.screen.AbstractScreen

interface DrawingAspect {
    fun draw(screen: AbstractScreen, x: Int = 0, y: Int = 0)
}