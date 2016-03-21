package eu.fizzystuff.krog.scenes

import com.googlecode.lanterna.input.KeyStroke

interface LogicNode {
    fun process(keyStroke: KeyStroke)
}