package eu.fizzystuff.krog.scenes

import com.googlecode.lanterna.input.KeyStroke

interface InputNode {
    fun process(keyStroke: KeyStroke)
}