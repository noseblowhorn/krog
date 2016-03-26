package eu.fizzystuff.krog.scenes

import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.screen.AbstractScreen

interface Scene {
    fun init()
    fun draw(screen: AbstractScreen)
    fun acceptInput(input: KeyStroke)
    fun tick()
}