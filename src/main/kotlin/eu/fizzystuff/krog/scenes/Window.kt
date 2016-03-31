package eu.fizzystuff.krog.scenes

import com.googlecode.lanterna.screen.AbstractScreen

interface Window {
    fun draw(screen: AbstractScreen, x: Int = 0, y: Int = 0)
}