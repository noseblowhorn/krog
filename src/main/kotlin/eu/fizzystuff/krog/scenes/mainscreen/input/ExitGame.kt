package eu.fizzystuff.krog.scenes.mainscreen.input

import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import eu.fizzystuff.krog.scenes.InputNode
import eu.fizzystuff.krog.scenes.LogicNode

class ExitGame : InputNode {
    override fun process(keyStroke: KeyStroke) {
        if (keyStroke.keyType == KeyType.Escape) {
            System.exit(0)
        }
    }
}